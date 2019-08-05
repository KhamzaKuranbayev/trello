package uz.genesis.trello.utils.validators;

import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 27/06/2019
 */

public abstract class BaseCrudValidator<T, C extends CrudDto, U extends CrudDto> extends AbstractValidator<T> {

    public BaseCrudValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    public void validateOnCreate(C domain) {
        baseValidation(domain);
    }

    public void validateDomainOnCreate(T domain) {
        baseValidation(domain, false);
    }

    public void validateOnUpdate(U domain) {
        baseValidation(domain);
    }

    public void validateDomainOnUpdate(T domain) {
        baseValidation(domain, true);
    }

    public abstract void baseValidation(CrudDto domain);

    public abstract void baseValidation(T domain, boolean idRequired);

    public void validateOnDelete(Long id) {
        if (id == null) {
            throw new IdRequiredException(repository.getErrorMessage(ID_REQUIRED, ""));
        }
    }
}
