package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.domain.main.TaskTag;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskTagRepository extends IGenericCrudRepository<TaskTag, TaskTagCriteria> {
}
