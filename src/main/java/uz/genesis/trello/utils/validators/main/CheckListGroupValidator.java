package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.CheckListGroup;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.CheckListGroupCreateDto;
import uz.genesis.trello.dto.main.CheckListGroupUpdateDto;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;
@Component
public class CheckListGroupValidator extends BaseCrudValidator<CheckListGroup, CheckListGroupCreateDto, CheckListGroupUpdateDto> {

    public CheckListGroupValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(CheckListGroup domain, boolean idRequired) {

    }
}
