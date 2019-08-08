package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.auth.UserOtp;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

/**
 * Created by 'javokhir' on 27/06/2019
 */

@Component
public class UserServiceValidator extends BaseCrudValidator<User, UserCreateDto, UserUpdateDto> {

    public UserServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }


    @Override
    public void baseValidation(CrudDto domain) {
        if (domain instanceof UserCreateDto) {
        } else {
        }
    }

    public void validateOnAuth(AuthUserDto authUserDto) {
        if (utils.isEmpty(authUserDto.getUserName()))
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("userName", User.class)), "userName");

        if (utils.isEmpty(authUserDto.getPassword()))
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("password", User.class)), "password");

    }

    public void validateOnOtpConfirm(UserOtpConfirmDto otpConfirmDto) {

        if (utils.isEmpty(otpConfirmDto.getUsername()))
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("userName", User.class)), "userName");

        if (utils.isEmpty(otpConfirmDto.getOtpCode()))
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("otpCode", UserOtp.class)), "otpCode");


    }

    public void validateOnAttach(AttachRoleDto attachRoleDto) {
        if (utils.isEmpty(attachRoleDto.getUserId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""), "userId");
        }
        if (utils.isEmpty(attachRoleDto.getRoles())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("roles", User.class)), "roles");
        }
    }

    @Override
    public void baseValidation(User domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""), "id");
        } else if (utils.isEmpty(domain.getEmail())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("email", User.class)), "email");
        }
        if (!utils.isValidEmail(domain.getEmail())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.EMAIL_NOT_VALID, utils.toErrorParams(domain.getEmail())), "email");
        } else if (utils.isEmpty(domain.getUserName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("userName", User.class)), "userName");
        } else if (utils.isEmpty(domain.getPassword())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("password", User.class)), "password");
        } else if (utils.isEmpty(domain.getOrganizationId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("organizationId", User.class)), "organizationId");
        }
    }
}
