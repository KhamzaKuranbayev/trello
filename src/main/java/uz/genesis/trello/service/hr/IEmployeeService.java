package uz.genesis.trello.service.hr;

import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

/**
 * Created by 'javokhir' on 04/07/2019
 */

public interface IEmployeeService extends IGenericCrudService<EmployeeDto, EmployeeCreateDto, EmployeeUpdateDto, Long, EmployeeCriteria> {


}
