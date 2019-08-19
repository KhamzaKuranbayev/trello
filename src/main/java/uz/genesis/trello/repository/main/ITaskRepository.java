package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.repository.IGenericCrudRepository;

import java.util.List;

public interface ITaskRepository extends IGenericCrudRepository<Task, TaskCriteria> {
    List<TaskDto> getAllDtoList(TaskCriteria criteria) throws Exception;
}
