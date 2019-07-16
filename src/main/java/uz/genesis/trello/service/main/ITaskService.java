package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.dto.main.TaskCreateDto;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.dto.main.TaskUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ITaskService extends IGenericCrudService<TaskDto, TaskCreateDto, TaskUpdateDto, Long, TaskCriteria> {
}
