package uz.genesis.trello.service.hr;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeGroupDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface IEmployeeGroupService extends IGenericCrudService<GenericDto, CrudDto, CrudDto, Long, EmployeeCriteria> {
    ResponseEntity<DataDto<List<EmployeeDto>>> getEmployee(EmployeeCriteria criteria);

    List<EmployeeGroupDto> getAllEmployeeGroup(EmployeeCriteria criteria);
}
