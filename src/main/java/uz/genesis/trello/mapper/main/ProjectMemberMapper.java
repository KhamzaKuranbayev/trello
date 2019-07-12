package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.dto.main.ProjectMemberCreateDto;
import uz.genesis.trello.dto.main.ProjectMemberDto;
import uz.genesis.trello.dto.main.ProjectMemberUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMemberMapper extends BaseMapper<ProjectMember, ProjectMemberDto, ProjectMemberCreateDto, ProjectMemberUpdateDto> {
}
