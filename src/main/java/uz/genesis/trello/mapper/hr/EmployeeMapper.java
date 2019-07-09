package uz.genesis.trello.mapper.hr;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto, EmployeeCreateDto, EmployeeUpdateDto> {

}
