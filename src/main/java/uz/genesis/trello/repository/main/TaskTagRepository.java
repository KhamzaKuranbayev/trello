package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.TaskTag;

import java.util.List;
import java.util.Map;

@Repository
public class TaskTagRepository  extends GenericDao<TaskTag, TaskTagCriteria> implements ITaskTagRepository{
    @Override
    protected void defineCriteriaOnQuerying(TaskTagCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getProjectTagId())){
            whereCause.add("t.projectTagId = :projectTagId");
            params.put("projectTagId", criteria.getProjectTagId());
        }
        if(!utils.isEmpty(criteria.getTaskId())){
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
