package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.ProjectColumnCriteria;
import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IProjectColumnService extends IGenericCrudService<ProjectColumnDto, ProjectColumnCreateDto, ProjectColumnUpdateDto, Long, ProjectColumnCriteria> {
}
