package uz.genesis.trello.repository.hr;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeDto;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository extends GenericDao<Employee, EmployeeCriteria> implements IEmployeeRepository {
    @Override
    protected void defineCriteriaOnQuerying(EmployeeCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.userId = :selfId");
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

        if ((!utils.isEmpty(criteria.getIsWatcher()) && criteria.getIsWatcher()) && (!utils.isEmpty(criteria.getIsManager()) && criteria.getIsManager())){
            whereCause.add(" g.watcher is true or rs.codeName = ('MANAGER') ");
        } else if (!utils.isEmpty(criteria.getIsWatcher()) && criteria.getIsWatcher()){
            whereCause.add(" g.watcher is true ");
        } else if (!utils.isEmpty(criteria.getIsManager()) && criteria.getIsManager()){
            whereCause.add(" rs.codeName = ('MANAGER') ");
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected void onDefineWhereCause(EmployeeCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        addOrganizationCheck(queryBuilder, params, "t");
        super.onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }

    @Override
    protected StringBuilder joinStringOnQuerying(EmployeeCriteria criteria) {
        StringBuilder joinBuilder = new StringBuilder();

        if ((!utils.isEmpty(criteria.getIsWatcher()) && criteria.getIsWatcher()) || (!utils.isEmpty(criteria.getIsManager()) && criteria.getIsManager())) {
            joinBuilder.append(" left join User us on us.id = t.userId ");
        }

        if (!utils.isEmpty(criteria.getIsWatcher()) && criteria.getIsWatcher()) {
            joinBuilder.append(" left join EmployeeGroup eg on eg.employeeId = us.id " +
                    " left join Group g on g.id = eg.groupId ");
        }
        if (!utils.isEmpty(criteria.getIsManager()) && criteria.getIsManager()) {
            joinBuilder.append(" left join User u on u.id = us.id " +
                    " left join u.roles rs ");
        }
        return joinBuilder;
    }

    @Override
    protected Query defineQuerySelect(EmployeeCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {

        String queryStr;
        if (!utils.isEmpty(criteria.getWithPhoto()) && criteria.getWithPhoto()) {
            queryStr = " select" + (onDefineCount ? " count(distinct t) " : " new uz.genesis.trello.dto.hr.EmployeeDto(distinct(t), getemployeephotourl(t.userId))") + " from  Employee t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return entityManager.createQuery(queryStr);
        } else {
            queryStr = " select " + (onDefineCount ? " count(distinct t) " : " distinct(t) ") + " from  Employee t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
        }
    }

    @Override
    public List<EmployeeDto> getEmployeesWithPhoto(EmployeeCriteria criteria) {
        return findAllGeneric(criteria);
    }
}
