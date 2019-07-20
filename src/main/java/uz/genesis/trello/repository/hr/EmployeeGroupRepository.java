package uz.genesis.trello.repository.hr;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.hr.EmployeeGroup;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeGroupRepository extends GenericDao<EmployeeGroup, EmployeeCriteria> implements IEmployeeGroupRepository {

    @Override
    protected void defineCriteriaOnQuerying(EmployeeCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getProjectId())) {
            if (!utils.isEmpty(criteria.getProjectRelated())) {
                String query = "p.id = :projectId and t.employeeId";
                query += criteria.getProjectRelated() ? " " : " not ";
                whereCause.add(query + "in (select pm.employee.id from ProjectMember pm where pm.projectId = p.id)");
            }
            else {
                whereCause.add("p.id = :projectId");
            }
            params.put("projectId", criteria.getProjectId());
        }

        if(!utils.isEmpty(criteria.getGroupId())){
            whereCause.add(" t.groupId = :groupId");
            params.put("groupId", criteria.getGroupId());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected StringBuilder joinStringOnQuerying(EmployeeCriteria criteria) {
        StringBuilder joinBuilder = new StringBuilder();
        if (!utils.isEmpty(criteria.getProjectId())) {
            joinBuilder.append(" inner join Project p on t.groupId = p.group.id ");
        }
        return joinBuilder;
    }
}
