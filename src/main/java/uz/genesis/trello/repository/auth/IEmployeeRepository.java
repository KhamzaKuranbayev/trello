package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.EmployeeCriteria;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IEmployeeRepository extends IGenericCrudRepository<Employee, EmployeeCriteria> {

}
