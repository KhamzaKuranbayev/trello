package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.domain.main.TaskAction;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskActionRepository extends IGenericCrudRepository<TaskAction, TaskActionCriteria> {
}
