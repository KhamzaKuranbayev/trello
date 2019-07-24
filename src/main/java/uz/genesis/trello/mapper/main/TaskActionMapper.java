package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.TaskAction;
import uz.genesis.trello.dto.main.TaskActionDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskActionMapper {
    List<TaskActionDto> toDto(List<TaskAction> taskActionList);
}
