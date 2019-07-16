package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskCheckListCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.TaskCheckList;

import java.util.List;
import java.util.Map;

@Repository
public class TaskCheckListRepository extends GenericDao<TaskCheckList, TaskCheckListCriteria> implements ITaskCheckListRepository {

    @Override
    protected void defineCriteriaOnQuerying(TaskCheckListCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getTaskId())) {
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }
        if (!utils.isEmpty(criteria.getText())) {
            whereCause.add("t.text = :text");
            params.put("text", criteria.getText());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
