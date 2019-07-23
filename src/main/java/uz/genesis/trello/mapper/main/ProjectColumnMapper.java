package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnDetailDto;
import uz.genesis.trello.dto.main.ProjectColumnDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProjectColumnMapper extends BaseMapper<ProjectColumn, ProjectColumnDto, ProjectColumnCreateDto, ProjectColumnUpdateDto> {
    List<ProjectColumnDetailDto> toDetailDto(List<ProjectColumn> projectColumnList);
}
