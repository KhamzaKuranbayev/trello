package uz.genesis.trello.service.file;

import uz.genesis.trello.criterias.file.BackgroundCriteria;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IBackgroundService extends IGenericCrudService<BackgroundDto, BackgroundCreateDto, BackgroundUpdateDto, Long, BackgroundCriteria> {

}
