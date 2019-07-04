package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.EmployeeCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository extends GenericDao<Employee, EmployeeCriteria> implements IEmployeeRepository {
    @Override
    protected void defineCriteriaOnQuerying(EmployeeCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getBirthDate())) {
            whereCause.add("t.getBirthDate = :birthDate");
            params.put("birthDate", criteria.getBirthDate());
        }
        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("lower(t.firstName) like :name or lower(t.lastName) like :name or lower(t.middleName) like :name");
            params.put("name", "%" + criteria.getName() + "%");
        } else {
            if (!utils.isEmpty(criteria.getFirstName())) {
                whereCause.add("lower(t.firstName) like :firstName");
                params.put("firstName", "%" + criteria.getFirstName().toLowerCase() + "%");
            } else if (!utils.isEmpty(criteria.getLastName())) {
                whereCause.add("lower(t.lastName) like :lastName");
                params.put("lastName", "%" + criteria.getLastName().toLowerCase() + "%");
            } else if (!utils.isEmpty(criteria.getMiddleName())) {
                whereCause.add("lower(t.middleName) like :middleName");
                params.put("middleName", "%" + criteria.getMiddleName().toLowerCase() + "%");
            }
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
