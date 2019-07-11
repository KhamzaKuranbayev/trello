package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.UserMapper;
import uz.genesis.trello.repository.auth.IUserRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.UserServiceValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Service
public class UserService extends AbstractCrudService<UserDto, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    /**
     * Common logger for use in subclasses.
     */
    private final Log logger = LogFactory.getLog(getClass());

    private UserMapper mapper;
    private GenericMapper genericMapper;
    private UserServiceValidator validator;

    private final PasswordEncoder oauthClientPasswordEncoder;

    public UserService(IUserRepository repository, BaseUtils utils, UserServiceValidator validator, UserMapper mapper, GenericMapper genericMapper, PasswordEncoder oauthClientPasswordEncoder) {
        super(repository, utils);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.oauthClientPasswordEncoder = oauthClientPasswordEncoder;
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> get(Long id) {
        User user = repository.find(UserCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("user with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataDto<>(mapper.fromUserToDto(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserCreateDto dto) {
        User user = mapper.fromCreateDto(dto);

        validator.validateDomainOnCreate(user);

        dto.setPassword(oauthClientPasswordEncoder.encode(dto.getPassword()));


        try {
            user.setId(repository.create(dto, "createUser"));
        } catch (Exception ex) {
            logger.error(ex);
            logger.error(String.format(" dto '%s' ", dto.toString()));
            throw new RuntimeException(ex);
        }

        if (utils.isEmpty(user.getId())) {
            logger.error(String.format("Non UserCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non UserCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(user)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> update(@NotNull UserUpdateDto dto) {

        validator.validateOnUpdate(dto);

        dto.setPassword(oauthClientPasswordEncoder.encode(dto.getPassword() == null ? "12345" : dto.getPassword()));

        if (repository.update(dto, "updateUser")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update user with id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteUser")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> attachRolesToUser(@NotNull AttachRoleDto dto){
        validator.validateOnAttach(dto);
        if(repository.call(dto, "attachRole", Types.BOOLEAN)){
            return get(dto.getId());
        } else {
            throw new RuntimeException((String.format("could not attach roles to user with id '%s", dto.getId())));
        }
    }


}
