package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.Task;

import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository extends GenericDao<Task, TaskCriteria> implements ITaskRepository {
    @Override
    protected void defineCriteriaOnQuerying(TaskCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getColumnId())){
            whereCause.add("t.columnId = :columnId");
            params.put("columnId", criteria.getColumnId());
        }
        if(!utils.isEmpty(criteria.getDeadLine())){
            whereCause.add("t.deadLine = :deadLine");
            params.put("deadLine", criteria.getDeadLine());
        }
        if(!utils.isEmpty(criteria.getDescription())){
            whereCause.add("t.description = :description");
            params.put("description", criteria.getDescription());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getDeadLine());
        }
        if(!utils.isEmpty(criteria.getProjectId())){
            whereCause.add("t.projectId = :projectId");
            params.put("projectId", criteria.getProjectId());
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
