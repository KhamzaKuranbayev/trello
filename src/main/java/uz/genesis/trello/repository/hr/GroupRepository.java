package uz.genesis.trello.repository.hr;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.hr.GroupCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.hr.Group;

import java.util.List;
import java.util.Map;

@Repository
public class GroupRepository extends GenericDao<Group, GroupCriteria> implements IGroupRepository {

    @Override
    protected void defineCriteriaOnQuerying(GroupCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }
        if (!utils.isEmpty(criteria.isWatcher())) {
            whereCause.add("t.watcher = :watcher");
            params.put("watcher", criteria.isWatcher());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected void onDefineWhereCause(GroupCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        addOrganizationCheck(queryBuilder, params, "t");
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
