package uz.genesis.trello.service.settings;

import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IErrorMessageService extends IGenericCrudService<ErrorMessageDto, ErrorMessageCreateDto, ErrorMessageUpdateDto, Long, ErrorMessageCriteria>  {
}
