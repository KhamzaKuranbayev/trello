package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskRepository extends IGenericCrudRepository<Task, TaskCriteria> {
}
