package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.main.CheckListGroup;
import uz.genesis.trello.dto.main.CheckListGroupCreateDto;
import uz.genesis.trello.dto.main.CheckListGroupDto;
import uz.genesis.trello.dto.main.CheckListGroupUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CheckListGroupMapper extends BaseMapper<CheckListGroup, CheckListGroupDto, CheckListGroupCreateDto, CheckListGroupUpdateDto> {


}
