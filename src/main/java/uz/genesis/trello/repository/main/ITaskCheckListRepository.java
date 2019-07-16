package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskCheckListCriteria;
import uz.genesis.trello.domain.main.TaskCheckList;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskCheckListRepository extends IGenericCrudRepository<TaskCheckList, TaskCheckListCriteria> {
}
