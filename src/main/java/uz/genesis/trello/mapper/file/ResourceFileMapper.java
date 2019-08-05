package uz.genesis.trello.mapper.file;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.files.ResourceFile;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.ResourceFileCreateDto;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface ResourceFileMapper extends BaseMapper<ResourceFile, ResourceFileDto, ResourceFileCreateDto, GenericCrudDto> {
    ResourceFileDto fromCreateDtoToDto(ResourceFileCreateDto dto);
}
