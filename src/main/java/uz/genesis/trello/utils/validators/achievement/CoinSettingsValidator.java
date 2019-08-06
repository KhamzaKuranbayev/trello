package uz.genesis.trello.utils.validators.achievement;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.achievement.CoinSettings;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;


@Component
public class CoinSettingsValidator extends BaseCrudValidator<CoinSettings, CrudDto, CrudDto> {


    public CoinSettingsValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(CoinSettings domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(CoinSettings.class)), "CoinSettings");
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""), "id");
        } else if (utils.isEmpty(domain.getSettingsType())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("settingsType", CoinSettings.class)), "settingsType");
        } else if (utils.isEmpty(domain.getCoins())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("coins", CoinSettings.class)), "coins");
        } else if (utils.isEmpty(domain.getOrganizationId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("organizationId", CoinSettings.class)), "organizationId");
        }
    }
}
