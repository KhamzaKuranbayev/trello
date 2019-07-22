package uz.genesis.trello.service.main;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ITaskService extends IGenericCrudService<TaskDto, TaskCreateDto, TaskUpdateDto, Long, TaskCriteria> {
    ResponseEntity<DataDto<TaskDto>> move(MovingTaskDto dto);
    ResponseEntity<DataDto<GenericDto>> createTaskTimeEntry(TaskTimeEntryCreateDto taskTimeEntryCreateDto);
}
