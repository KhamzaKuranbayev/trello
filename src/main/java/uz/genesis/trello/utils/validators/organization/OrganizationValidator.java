package uz.genesis.trello.utils.validators.organization;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.organization.OrganizationUserDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class OrganizationValidator extends BaseCrudValidator<Organization, OrganizationUserDto, CrudDto> {
    public OrganizationValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {
    }

    @Override
    public void baseValidation(Organization domain, boolean idRequired) {
    }

    public void validateOnAuth(OrganizationUserDto organizationUserDto) {
        if (utils.isEmpty(organizationUserDto.getPrefix())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("prefix", Organization.class)), "prefix");
        }
        if (utils.isEmpty(organizationUserDto.getName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("name", Organization.class)), "name");
        }
        if (utils.isEmpty(organizationUserDto.getUserName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("userName", User.class)), "userName");
        }

        if (utils.isEmpty(organizationUserDto.getEmail()) && utils.isEmpty(organizationUserDto.getPhoneNumber())) {
            if (utils.isEmpty(organizationUserDto.getEmail())) {
                throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("email or phoneNumber", User.class)), "email");
            }
        }
        if (utils.isEmpty(organizationUserDto.getOrganizationEmail())){
            throw  new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("email", Organization.class)), "email");
        }
        if (!utils.isEmpty(organizationUserDto.getOrganizationEmail())) {
            if (!utils.isValidEmail(organizationUserDto.getOrganizationEmail())) {
                throw new ValidationException(repository.getErrorMessage(ErrorCodes.EMAIL_NOT_VALID, utils.toErrorParams(organizationUserDto.getOrganizationEmail())), "organizationEmail");
            }
        }
        if (!utils.isEmpty(organizationUserDto.getEmail())) {
            if (!utils.isValidEmail(organizationUserDto.getEmail())) {
                throw new ValidationException(repository.getErrorMessage(ErrorCodes.EMAIL_NOT_VALID, utils.toErrorParams(organizationUserDto.getEmail())), "email");
            }
        }
        if (utils.isEmpty(organizationUserDto.getFirstName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("firstName", User.class)), "firstName");
        }

    }
}
