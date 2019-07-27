package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.UserLastLoginCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.UserLastLogin;

import java.util.List;
import java.util.Map;

@Repository
public class UserLastLoginRepository extends GenericDao<UserLastLogin, UserLastLoginCriteria> implements IUserLastLoginRepository {
    @Override
    protected void defineCriteriaOnQuerying(UserLastLoginCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getSessionToken())){
            whereCause.add("t.sessionToken = :sessionToken");
            params.put("sessionToken", criteria.getSessionToken());
        }
        if(!utils.isEmpty(criteria.getIpAddress())){
            whereCause.add("t.ipAddress = :ipAddress");
            params.put("ipAddress", criteria.getIpAddress());
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
