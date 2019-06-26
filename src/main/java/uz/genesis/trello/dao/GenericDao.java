package uz.genesis.trello.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.enums.Headers;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.UserSession;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by 'javokhir' on 10/06/2019
 */

public abstract class GenericDao<T extends Auditable, C extends GenericCriteria> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    private Class<T> persistentClass;

    @Autowired
    protected EntityManager entityManager;
    @Autowired
    protected BaseUtils utils;

    @Autowired
    protected UserSession userSession;

    protected Gson gson;
    protected JpaEntityInformation<T, ?> entityInformation;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        initEntityInformation();
    }

    private void initEntityInformation() {
        if (entityManager != null && entityInformation == null) {
            this.entityInformation = JpaEntityInformationSupport.getEntityInformation(persistentClass, entityManager);
        }
    }

    public T find(Long id) {
        return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t where t.deleted = 0 and t.id = " + id, persistentClass).getSingleResult();
    }

    protected Optional<T> findById(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        return Optional.ofNullable(find(id));
    }

    public T find(C criteria) {
        Query query = findInit(criteria, false);

        try {
            return (T) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
//        throw new RuntimeException("No result found");
    }

    public List<T> findAll(C criteria) {
        return findAllGeneric(criteria);
    }

    protected <G> List<G> findAllGeneric(C criteria) {
        Query query = findInit(criteria, false);
        return getResults(criteria, query);
    }

    public Long getTotalCount(C criteria) {
        Query query = findInit(criteria, true);
        return (Long) query.getSingleResult();
    }

    private Query findInit(C criteria, boolean onDefineCount) {
        Query query;
        Map<String, Object> params = new HashMap<>();
        List<String> whereCause = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();

        defineCriteriaOnQuerying(criteria, whereCause, params, queryBuilder);

        query = defineQuerySelect(criteria, queryBuilder, onDefineCount);

        defineSetterParams(query, params);

        return query;
    }

    private void defineSetterParams(Query query, Map<String, Object> params) {
        params.keySet().forEach(t -> query.setParameter(t, params.get(t)));
    }

    protected void defineCriteriaOnQuerying(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    protected void onDefineWhereCause(C criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!whereCause.isEmpty()) {
            queryBuilder.append(" and ").append(StringUtils.join(whereCause, " and "));
        }
    }

    protected Query defineQuerySelect(C criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr = " select " + (onDefineCount ? " count(t) " : " t ") + " from " + persistentClass.getSimpleName() + " t " +
                joinStringOnQuerying(criteria) +
                " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
        return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
    }

    protected StringBuilder joinStringOnQuerying(C criteria) {
        return new StringBuilder();
    }

    protected StringBuilder onSortBy(C criteria) {
        StringBuilder sorting = new StringBuilder();
        if (!utils.isEmpty(criteria.getSortBy())) {
            String ascDesc = criteria.getSortDirection();
            sorting.append(" order by ").append("t.").append(criteria.getSortBy()).append(" ").append(ascDesc);
        }
        return sorting;
    }

    protected static void addLanguageWhereCause(Map<String, Object> params, Map<Headers, String> headers, List<String> whereCause, String alias) {
        if (headers.containsKey(Headers.LANGUAGE)) {
            whereCause.add(alias + ".language.code =:" + Headers.LANGUAGE.key);
            params.put(Headers.LANGUAGE.key, headers.get(Headers.LANGUAGE));
        } else {
            whereCause.add(alias + ".language.code =:" + Headers.LANGUAGE.key);
            params.put(Headers.LANGUAGE.key, Headers.LANGUAGE.defValue);
        }
    }

    protected <G> List<G> getResults(C criteria, Query query) {
        if ((criteria.getPage() == null || criteria.getPerPage() == null) || (criteria.getPage() < 0 || criteria.getPerPage() <= 0)) {
            return query.getResultList();
        } else {
            return query.setFirstResult(criteria.getPage() * criteria.getPerPage())
                    .setMaxResults(criteria.getPerPage()).getResultList();
        }
    }

    @Transactional
    public  <S extends T> S save(S entity) {
        initEntityInformation();
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Transactional
    public <S extends T> List<S> saveAll(Iterable<S> entities) {

        Assert.notNull(entities, "The given Iterable of entities not be null!");

        List<S> result = new ArrayList<S>();

        for (S entity : entities) {
            result.add(save(entity));
        }

        return result;
    }

    @Transactional
    public void delete(T entity) {

        Assert.notNull(entity, "The entity must not be null!");
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Transactional
    public void deleteById(Long id) {
        initEntityInformation();
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1)));
    }

    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {

        Assert.notNull(entities, "The given Iterable of entities not be null!");

        for (T entity : entities) {
            delete(entity);
        }
    }
}
