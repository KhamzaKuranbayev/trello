package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.settings.SubTypeCreateDto;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Component
public class TypeServiceValidator extends BaseCrudValidator<Type, TypeCreateDto, TypeUpdateDto> {

    public TypeServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    public void validateOnSubTypeCreate(SubTypeCreateDto subTypeCreateDto) {
        if (utils.isEmpty(subTypeCreateDto)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(Type.class)));
        } else if (utils.isEmpty(subTypeCreateDto.getTypeCode())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("typeCode", Type.class)));
        }
    }

    @Override
    public void baseValidation(Type domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(Type.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        } else if (utils.isEmpty(domain.getName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("name", Type.class)));
        } else if (utils.isEmpty(domain.getValue())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("value", Type.class)));
        }
    }

}
