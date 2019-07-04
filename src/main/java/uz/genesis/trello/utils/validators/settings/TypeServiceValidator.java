package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Component
public class TypeServiceValidator extends BaseCrudValidator<Type> {


    public TypeServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(Type domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Type.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        } else if (utils.isEmpty(domain.getValue())) {

        }
    }

}
