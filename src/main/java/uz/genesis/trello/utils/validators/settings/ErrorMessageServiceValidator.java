package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class ErrorMessageServiceValidator extends BaseCrudValidator<ErrorMessage, ErrorMessageCreateDto, ErrorMessageUpdateDto> {

    public ErrorMessageServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(ErrorMessage domain, boolean idRequired) {

    }
}
