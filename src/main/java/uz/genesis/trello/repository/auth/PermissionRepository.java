package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.PermissionCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.Permission;

import java.util.List;
import java.util.Map;

@Repository
public class PermissionRepository extends GenericDao<Permission, PermissionCriteria> implements IPermissionRepository {

    @Override
    protected void onDefineWhereCause(PermissionCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getCodeName())){
            whereCause.add("t.codeName = :codeName");
            params.put("codeName", criteria.getCodeName());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
