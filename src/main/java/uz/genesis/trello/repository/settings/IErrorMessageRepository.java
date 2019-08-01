package uz.genesis.trello.repository.settings;

import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IErrorMessageRepository extends IGenericCrudRepository<ErrorMessage, ErrorMessageCriteria> {
}
