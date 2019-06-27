package uz.genesis.trello.utils.validators;

import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.utils.BaseUtils;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 27/06/2019
 */

public abstract class BaseCrudValidator<T> extends AbstractValidator<T> {

    public BaseCrudValidator(BaseUtils utils) {
        super(utils);
    }

    public void validateOnCreate(T domain){
        baseValidation(domain, false);
    }

    public void validateOnUpdate(T domain){
        baseValidation(domain, true);
    }

    public abstract void baseValidation(T domain, boolean idRequired);

    public void validateOnDelete(Long id) {
        if (id == null) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        }
    }
}
