package uz.genesis.trello.repository.main;

import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.dto.main.ProjectPercentageDto;
import uz.genesis.trello.repository.IGenericCrudRepository;

import java.util.List;

public interface IProjectRepository extends IGenericCrudRepository<Project, ProjectCriteria> {

    List<ProjectPercentageDto> getAllPercentageProjects(ProjectCriteria criteria);
}
