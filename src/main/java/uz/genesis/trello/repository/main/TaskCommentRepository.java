package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.TaskComment;

import java.util.List;
import java.util.Map;

@Repository
public class TaskCommentRepository extends GenericDao<TaskComment, TaskCommentCriteria> implements ITaskCommentRepository {
    @Override
    protected void defineCriteriaOnQuerying(TaskCommentCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getCommentText())){
            whereCause.add("t.commentText = :commentText");
            params.put("commentText", criteria.getCommentText());
        }
        if(!utils.isEmpty(criteria.getTaskId())){
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
