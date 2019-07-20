package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.TaskMember;
import uz.genesis.trello.dto.main.TaskMemberCreateDto;
import uz.genesis.trello.dto.main.TaskMemberDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMemberMapper  {
    TaskMemberDto toDto(TaskMember domain);
    List<TaskMemberDto> toDto(List<TaskMember> domain);
    TaskMember fromCreateDto(TaskMemberCreateDto dto);
}
