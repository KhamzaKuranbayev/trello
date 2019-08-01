package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.settings.ErrorMessage;

@Repository
public class ErrorMessageRepository extends GenericDao<ErrorMessage, ErrorMessageCriteria> implements IErrorMessageRepository {
}
