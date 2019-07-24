package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.dto.main.TaskActionDto;
import uz.genesis.trello.service.IGenericService;

import java.util.List;

public interface ITaskActionService  extends IGenericService<TaskActionDto, Long, TaskActionCriteria> {
    List<TaskActionDto> getAllTaskAction(TaskActionCriteria criteria);
}
