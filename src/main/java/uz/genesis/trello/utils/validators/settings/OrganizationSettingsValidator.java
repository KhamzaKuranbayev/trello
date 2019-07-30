package uz.genesis.trello.utils.validators.settings;

import org.springframework.stereotype.Component;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsCreateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class OrganizationSettingsValidator extends BaseCrudValidator<OrganizationSettingsDto, OrganizationSettingsCreateDto, OrganizationSettingsUpdateDto> {
    public OrganizationSettingsValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(OrganizationSettingsDto domain, boolean idRequired) {

    }
}
