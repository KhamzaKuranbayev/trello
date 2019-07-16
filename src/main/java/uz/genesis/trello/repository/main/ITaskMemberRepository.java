package uz.genesis.trello.repository.main;


import uz.genesis.trello.criterias.main.TaskMemberCriteria;
import uz.genesis.trello.domain.main.TaskMember;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ITaskMemberRepository extends IGenericCrudRepository<TaskMember, TaskMemberCriteria> {
}
