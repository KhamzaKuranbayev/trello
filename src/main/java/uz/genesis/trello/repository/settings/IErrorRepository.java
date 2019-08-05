package uz.genesis.trello.repository.settings;

import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.repository.IGenericRepository;

public interface IErrorRepository extends IGenericRepository<Auditable, GenericCriteria> {
    String getErrorMessage(String errorCode, String params);

    String getErrorMessage(ErrorCodes errorCode, String params);
}
