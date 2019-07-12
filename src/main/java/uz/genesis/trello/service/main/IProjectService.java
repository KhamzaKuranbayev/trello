package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IProjectService extends IGenericCrudService<ProjectDto, ProjectCreateDto, ProjectUpdateDto, Long, ProjectCriteria> {
}
