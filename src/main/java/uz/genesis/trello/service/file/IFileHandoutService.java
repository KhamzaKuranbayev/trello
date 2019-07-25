package uz.genesis.trello.service.file;

import uz.genesis.trello.criterias.file.FileHandoutCriteria;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.FileHandoutCreateDto;
import uz.genesis.trello.dto.file.FileHandoutDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IFileHandoutService extends IGenericCrudService<FileHandoutDto, FileHandoutCreateDto, GenericCrudDto, Long, FileHandoutCriteria> {
    Long createFileHandout(FileHandoutCreateDto dto);
}
