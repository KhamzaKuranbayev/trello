package uz.genesis.trello.repository.hr;

import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IEmployeeRepository extends IGenericCrudRepository<Employee, EmployeeCriteria> {

}
