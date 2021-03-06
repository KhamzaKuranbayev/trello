package uz.genesis.trello.repository.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.mapper.BaseObjectMapper;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository extends GenericDao<Task, TaskCriteria> implements ITaskRepository {
    private final BaseObjectMapper baseObjectMapper;

    @Autowired
    public TaskRepository(BaseObjectMapper baseObjectMapper) {
        this.baseObjectMapper = baseObjectMapper;
    }

    @Override
    protected void defineCriteriaOnQuerying(TaskCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getColumnId())) {
            whereCause.add("t.columnId = :columnId");
            params.put("columnId", criteria.getColumnId());
        }
        if (!utils.isEmpty(criteria.getDeadLine())) {
            whereCause.add("t.deadLine = :deadLine");
            params.put("deadLine", criteria.getDeadLine());
        }
        if (!utils.isEmpty(criteria.getDescription())) {
            whereCause.add("t.description = :description");
            params.put("description", criteria.getDescription());
        }
        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("t.name = :name");
            params.put("name", criteria.getDeadLine());
        }
        if (!utils.isEmpty(criteria.getProjectId())) {
            whereCause.add("t.projectId = :projectId");
            params.put("projectId", criteria.getProjectId());
        }

        if (!utils.isEmpty(criteria.getOwnTask()) && criteria.getOwnTask()) {
            whereCause.add("tm.employee.id = :userId");
            params.put("userId", userSession.getUser().getId());
        } else {
            if (!isAdmin()) {
                whereCause.add("pm.employee.id = :userId");
                params.put("userId", userSession.getUser().getId());
            }
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected void onDefineWhereCause(TaskCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        addOrganizationCheck(queryBuilder, params, "p");
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected Query defineQuerySelect(TaskCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr = " select " + (onDefineCount ? " count(t) " : " t, definetaskstatus(t.id, " + userSession.getUser().getId() + ") ") + " from Task t " +
                joinStringOnQuerying(criteria) +
                " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
        return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
    }

    @Override
    protected StringBuilder joinStringOnQuerying(TaskCriteria criteria) {
        StringBuilder joinBuilder = new StringBuilder();

        if (!utils.isEmpty(criteria.getOwnTask()) && criteria.getOwnTask()) {
            joinBuilder.append(" inner join TaskMember tm on t.id = tm.taskId and tm.deleted is false ");
        } else {
            joinBuilder.append(" left join Project p on p.id = t.projectId and p.deleted is false ");
            if (!isAdmin()) {
                joinBuilder.append(" inner join ProjectMember pm on t.projectId = pm.projectId and pm.deleted is false ");
            }
        }

        return joinBuilder;
    }

    @Override
    public List<TaskDto> getAllDtoList(TaskCriteria criteria) {
        List<Object[]> resultList = super.getAllCustomDtoList(criteria);
        try {
            return baseObjectMapper.toDtoList(resultList, "toDto");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());

        }
    }


}
