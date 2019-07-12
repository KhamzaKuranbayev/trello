package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;
@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, ProjectDto, ProjectCreateDto, ProjectUpdateDto> {
}
