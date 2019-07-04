package uz.genesis.trello.mapper.hr;

import org.mapstruct.Mapper;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    Employee fromCreateDto(EmployeeCreateDto dto);
}
