package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
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
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

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
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(User.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        } else if (utils.isEmpty(domain.getErrorCode())) {
            throw new ValidationException(" error code is required");
        } else if (utils.isEmpty(domain.getTranslations()) || domain.getTranslations().size() < 1) {
            throw new ValidationException("Translations should not be empty");
        }
    }

    public void validateForTranslations(List<ErrorMessageTranslationCreateDto> dto) {
        AtomicBoolean validated = new AtomicBoolean(false);
        dto.forEach(object -> {
            if (LanguageType.RU.name().equalsIgnoreCase(object.getLangCode())) {
                validated.set(true);
            }
        });

        if (!validated.get()) throw new ValidationException("Russian language should not be null or empty");
    }

}
