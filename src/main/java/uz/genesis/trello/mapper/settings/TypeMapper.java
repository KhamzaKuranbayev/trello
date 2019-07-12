package uz.genesis.trello.mapper.settings;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TypeMapper extends BaseMapper<Type, TypeDto, TypeCreateDto, TypeUpdateDto> {
}
