package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskTag;
import uz.genesis.trello.dto.main.TaskTagCreateDto;
import uz.genesis.trello.dto.main.TaskTagDto;
import uz.genesis.trello.dto.main.TaskTagUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface TaskTagMapper extends BaseMapper<TaskTag, TaskTagDto, TaskTagCreateDto, TaskTagUpdateDto> {
}
