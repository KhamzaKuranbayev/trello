package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.mapper.BaseMapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface TaskMapper extends BaseMapper<Task, TaskDto, TaskCreateDto, TaskUpdateDto> {

    Task fromMoveDto(MovingTaskDto dto);

    List<TaskProjectDetailDto> toTaskProjectDetailDto(List<Task> taskList);

}
