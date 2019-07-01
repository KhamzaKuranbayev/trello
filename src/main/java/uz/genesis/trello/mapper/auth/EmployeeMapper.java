package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.auth.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
}
