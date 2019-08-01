package uz.genesis.trello.mapper.settings;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.settings.Language;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.settings.LanguageDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LanguageMapper extends BaseMapper<Language, LanguageDto, GenericCrudDto, GenericCrudDto> {
}
