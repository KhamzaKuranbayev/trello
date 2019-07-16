package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.CheckListMemberCriteria;
import uz.genesis.trello.domain.main.CheckListMember;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ICheckListMemberRepository extends IGenericCrudRepository<CheckListMember, CheckListMemberCriteria> {
}
