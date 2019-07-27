package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.User;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by 'javokhir' on 11/06/2019
 */

@Repository
public class UserRepository extends GenericDao<User, UserCriteria> implements IUserRepository {

    @Override
    protected void defineCriteriaOnQuerying(UserCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (!utils.isEmpty(criteria.getUserName())) {
            whereCause.add("t.userName = :userName");
            params.put("userName", criteria.getUserName());
        }

        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }



        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected Query defineQuerySelect(UserCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr;
        if (criteria.isOnlyId()) {
            queryStr = " select" + (onDefineCount ? " count(t) " : " t.id " ) + " from  User t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return entityManager.createQuery(queryStr);
        } else {
            queryStr = " select " + (onDefineCount ? " count(t) " : " t ") + " from  User t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
        }
    }

//    @Override
//    public Long getId(UserCriteria criteria) {
//        return super.(criteria);
//    }


}
