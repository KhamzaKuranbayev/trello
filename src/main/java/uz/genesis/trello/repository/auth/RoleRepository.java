package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.Role;

import java.util.List;
import java.util.Map;
@Repository
public class RoleRepository extends GenericDao<Role, RoleCriteria> implements IRoleRepository {

    @Override
    protected void defineCriteriaOnQuerying(RoleCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (!utils.isEmpty(criteria.getCodeName())) {
            whereCause.add("t.codeName = :codeName");
            params.put("codeName", criteria.getCodeName());
        }

        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getRoleName())){
            whereCause.add(("t.roleName = :roleName"));
            params.put("roleName", criteria.getRoleName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected void onDefineWhereCause(RoleCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        addOrganizationCheck(queryBuilder, params, "t");
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
