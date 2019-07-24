package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.TaskAction;

import java.util.List;
import java.util.Map;

@Repository
public class TaskActionRepository extends GenericDao<TaskAction, TaskActionCriteria>  implements  ITaskActionRepository{
    @Override
    protected void defineCriteriaOnQuerying(TaskActionCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getProjectColumnId())){
            whereCause.add("t.projectColumnId = :projectColumnId");
            params.put("projectColumnId", criteria.getProjectColumnId());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }
        if(!utils.isEmpty(criteria.getTaskId())){
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }
        if(!utils.isEmpty(criteria.getProjectId())){
            whereCause.add("t.projectId = :projectId");
            params.put("projectId", criteria.getProjectId());
        }
        if(!utils.isEmpty(criteria.getTextParams())){
            whereCause.add("t.textParams = :textParams");
            params.put("textParams", criteria.getTextParams());
        }




        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
