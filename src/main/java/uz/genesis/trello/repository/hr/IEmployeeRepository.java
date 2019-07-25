package uz.genesis.trello.repository.hr;

import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.repository.IGenericCrudRepository;

import java.util.List;

public interface IEmployeeRepository extends IGenericCrudRepository<Employee, EmployeeCriteria> {
    List<EmployeeDto> getEmployeesWithPhoto(EmployeeCriteria criteria);
}
