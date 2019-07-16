package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskMemberCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.TaskMember;

import java.util.List;
import java.util.Map;

@Repository
public class TaskMemberRepository extends GenericDao<TaskMember, TaskMemberCriteria> implements ITaskMemberRepository {
    @Override
    protected void defineCriteriaOnQuerying(TaskMemberCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getTaskId())){
            whereCause.add("t.taskId = :taskId");
            params.put("taskId", criteria.getTaskId());
        }
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
