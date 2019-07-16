package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.domain.main.TaskComment;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskCommentRepository extends IGenericCrudRepository<TaskComment, TaskCommentCriteria> {
}
