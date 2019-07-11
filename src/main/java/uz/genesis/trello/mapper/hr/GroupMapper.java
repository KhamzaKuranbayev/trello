package uz.genesis.trello.mapper.hr;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.dto.hr.GroupCreateDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.dto.hr.GroupUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GroupMapper extends BaseMapper<Group, GroupDto, GroupCreateDto, GroupUpdateDto> {
}
