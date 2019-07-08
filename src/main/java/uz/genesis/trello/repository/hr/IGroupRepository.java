package uz.genesis.trello.repository.hr;

import uz.genesis.trello.criterias.hr.GroupCriteria;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IGroupRepository extends IGenericCrudRepository<Group, GroupCriteria> {
}
