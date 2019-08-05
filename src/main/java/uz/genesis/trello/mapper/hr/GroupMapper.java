package uz.genesis.trello.mapper.hr;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.dto.hr.GroupCreateDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.dto.hr.GroupUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface GroupMapper extends BaseMapper<Group, GroupDto, GroupCreateDto, GroupUpdateDto> {
}
