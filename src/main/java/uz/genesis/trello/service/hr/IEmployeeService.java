package uz.genesis.trello.service.hr;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.auth.EmployeeCriteria;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;
import uz.genesis.trello.service.IGenericService;

/**
 * Created by 'javokhir' on 04/07/2019
 */

public interface IEmployeeService extends IGenericCrudService<EmployeeDto, EmployeeCreateDto, EmployeeUpdateDto, Long, EmployeeCriteria> {


}
