package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.hr.EmployeeGroup;
import uz.genesis.trello.dto.hr.EmployeeGroupDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EmployeeGroupMapper {
    List<EmployeeGroupDto> toDto(List<EmployeeGroup> employeeGroups);
}
