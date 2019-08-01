package uz.genesis.trello.mapper.settings;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ErrorMessageMapper extends BaseMapper<ErrorMessage, ErrorMessageDto, ErrorMessageCreateDto, ErrorMessageUpdateDto> {

}
