package uz.genesis.trello.utils.validators.file;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.files.Background;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class BackgroundServiceValidator extends BaseCrudValidator<Background, BackgroundCreateDto, BackgroundUpdateDto> {
    public BackgroundServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(Background domain, boolean idRequired) {

    }


}
