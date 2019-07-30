package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.AuthTryCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.AuthTry;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class AuthTryRepository extends GenericDao<AuthTry, AuthTryCriteria> implements IAuthTryRepository {
    @Override
    protected void defineCriteriaOnQuerying(AuthTryCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getIpAddress())){
            whereCause.add("t.ipAddress = :ipAddress");
            params.put("ipAddress", criteria.getIpAddress());
        }
        if(!utils.isEmpty(criteria.getUserName())){
            whereCause.add("t.userName = :userName");
            params.put("userName", criteria.getUserName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected Query defineQuerySelect(AuthTryCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        return super.defineQuerySelect(criteria, queryBuilder, onDefineCount);
    }
}
