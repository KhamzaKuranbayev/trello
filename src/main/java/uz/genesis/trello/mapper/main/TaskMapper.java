package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.dto.main.MovingTaskDto;
import uz.genesis.trello.dto.main.TaskCreateDto;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.dto.main.TaskUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends BaseMapper<Task, TaskDto, TaskCreateDto, TaskUpdateDto> {

    Task fromMoveDto(MovingTaskDto dto);
}
