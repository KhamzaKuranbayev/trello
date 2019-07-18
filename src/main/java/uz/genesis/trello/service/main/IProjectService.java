package uz.genesis.trello.service.main;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectDto;
import uz.genesis.trello.dto.main.ProjectPercentageDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface IProjectService extends IGenericCrudService<ProjectDto, ProjectCreateDto, ProjectUpdateDto, Long, ProjectCriteria> {
    ResponseEntity<DataDto<List<ProjectPercentageDto>>> getAllWithPercentage(ProjectCriteria criteria);
}
