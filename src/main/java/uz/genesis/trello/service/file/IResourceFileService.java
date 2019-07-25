package uz.genesis.trello.service.file;

import uz.genesis.trello.criterias.file.ResourceFileCriteria;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.ResourceFileCreateDto;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IResourceFileService extends IGenericCrudService<ResourceFileDto, ResourceFileCreateDto, GenericCrudDto, Long, ResourceFileCriteria> {
    ResourceFileDto createFile(ResourceFileCreateDto dto);
}
