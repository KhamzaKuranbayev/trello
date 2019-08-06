package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageTranslationCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.enums.LanguageType;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ErrorMessageServiceValidator extends BaseCrudValidator<ErrorMessage, ErrorMessageCreateDto, ErrorMessageUpdateDto> {

    public ErrorMessageServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }


    @Override
    public void baseValidation(ErrorMessage domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(ErrorMessage.class)), "errorMessage");
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""), "id");
        }  else if (utils.isEmpty(domain.getErrorCode())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("errorCode", ErrorMessage.class)), "errorCode");
        } else if (utils.isEmpty(domain.getTranslations()) || domain.getTranslations().size() < 1) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("translations", ErrorMessage.class)), "translations");
        }
    }

    public void validateForTranslations(List<ErrorMessageTranslationCreateDto> dto) {
        AtomicBoolean validated = new AtomicBoolean(false);
        dto.forEach(object -> {
            if (LanguageType.RU.name().equalsIgnoreCase(object.getLangCode())) {
                validated.set(true);
            }
        });

        if (!validated.get()) throw new ValidationException(repository.getErrorMessage(ErrorCodes.RUSSIAN_LANGUAGE_SHOULD_NOT_BE_NULL, ""), "atomicBoolean");
    }

}
