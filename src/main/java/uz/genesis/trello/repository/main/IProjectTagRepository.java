package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.ProjectTagCriteria;
import uz.genesis.trello.domain.main.ProjectTag;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IProjectTagRepository extends IGenericCrudRepository<ProjectTag, ProjectTagCriteria> {
}
