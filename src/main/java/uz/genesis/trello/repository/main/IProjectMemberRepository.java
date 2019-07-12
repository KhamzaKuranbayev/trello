package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IProjectMemberRepository extends IGenericCrudRepository<ProjectMember, ProjectMemberCriteria> {
}
