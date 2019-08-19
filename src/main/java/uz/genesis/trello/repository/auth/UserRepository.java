package uz.genesis.trello.repository.auth;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.exception.CustomSqlException;

import javax.persistence.Query;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
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


    public boolean isAdmin(String userName) {
        return hasRole("ADMIN", userName);
    }

    @Override
    protected Query defineQuerySelect(UserCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr;
        if (criteria.isOnlyId()) {
            queryStr = " select" + (onDefineCount ? " count(t) " : " t.id ") + " from  User t " +
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

    public String getUniqueAuthId(Long userId) {
        Session session = entityManager.unwrap(Session.class);
        return session.doReturningWork(
                connection -> {
                    try (CallableStatement function = connection
                            .prepareCall(
                                    "{ ? = call getuniqeauthid (?) }")) {
                        function.registerOutParameter(1, Types.VARCHAR);
                        function.setLong(2, userId);
                        function.execute();

                        if (!utils.isEmpty(function.getWarnings())) {
                            throw new RuntimeException(function.getWarnings().getMessage());
                        }

                        return function.getString(1);
                    } catch (Exception ex) {
                        throw new CustomSqlException(ex.getMessage(), ex.getCause());
                    }
                });
    }
    @Override
    protected void onDefineWhereCause(UserCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!criteria.isForAuthenticate()) {
            addOrganizationCheck(queryBuilder, params, "t");
        }
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
