package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IProjectRepository extends IGenericCrudRepository<Project, ProjectCriteria> {
}
