package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.CheckListGroupCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.CheckListGroup;

import java.util.List;
import java.util.Map;

@Repository
public class CheckListGroupRepository extends GenericDao<CheckListGroup, CheckListGroupCriteria> implements ICheckListGroupRepository{

    @Override
    protected void defineCriteriaOnQuerying(CheckListGroupCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getTaskId())){
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
