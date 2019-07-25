package uz.genesis.trello.mapper.file;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.files.FileHandout;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.FileHandoutCreateDto;
import uz.genesis.trello.dto.file.FileHandoutDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FileHandoutMapper extends BaseMapper<FileHandout, FileHandoutDto, FileHandoutCreateDto, GenericCrudDto> {
}
