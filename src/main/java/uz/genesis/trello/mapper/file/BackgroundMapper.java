package uz.genesis.trello.mapper.file;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.files.Background;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BackgroundMapper extends BaseMapper<Background, BackgroundDto, BackgroundCreateDto, BackgroundUpdateDto> {
}
