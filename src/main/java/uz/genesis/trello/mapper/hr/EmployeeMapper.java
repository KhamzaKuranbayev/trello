package uz.genesis.trello.mapper.hr;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto, EmployeeCreateDto, EmployeeUpdateDto> {
    @Override
    @Mapping(ignore = true , target = "roles")
    EmployeeDto toDto(Employee employee);

    @Mapping(source = "userId", target = "id")
    Employee fromUpdateDto(EmployeeUpdateDto dto);
}
