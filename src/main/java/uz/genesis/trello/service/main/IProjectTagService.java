package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.ProjectTagCriteria;
import uz.genesis.trello.dto.main.ProjectTagCreateDto;
import uz.genesis.trello.dto.main.ProjectTagDto;
import uz.genesis.trello.dto.main.ProjectTagUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface IProjectTagService extends IGenericCrudService<ProjectTagDto, ProjectTagCreateDto, ProjectTagUpdateDto, Long, ProjectTagCriteria> {
    List<ProjectTagDto> getAllTag(ProjectTagCriteria criteria);
}
