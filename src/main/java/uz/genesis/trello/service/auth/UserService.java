package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.AttachRoleDto;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.auth.UserUpdateDto;
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
import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Service
@CacheConfig(cacheNames = "users")
public class UserService extends AbstractCrudService<UserDto, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    /**
     * Common logger for use in subclasses.
     */
    private final Log logger = LogFactory.getLog(getClass());
    private final PasswordEncoder oauthClientPasswordEncoder;
    private UserMapper mapper;
    private GenericMapper genericMapper;
    private UserServiceValidator validator;

    public UserService(IUserRepository repository, BaseUtils utils, UserServiceValidator validator, UserMapper mapper, GenericMapper genericMapper, PasswordEncoder oauthClientPasswordEncoder) {
        super(repository, utils);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.oauthClientPasswordEncoder = oauthClientPasswordEncoder;
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_READ)")
    public ResponseEntity<DataDto<UserDto>> get(Long id) {
        User user = repository.find(UserCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("user with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(user)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserCreateDto dto) {
        User user = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(user);
        dto.setPassword(oauthClientPasswordEncoder.encode(dto.getPassword()));
        user.setId(repository.create(dto, "createUser"));
        if (utils.isEmpty(user.getId())) {
            logger.error(String.format("Non UserCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non UserCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(user)), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_UPDATE)")
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
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteUser")), HttpStatus.OK);
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_ATTACH_ROLE)")
    public ResponseEntity<DataDto<UserDto>> attachRolesToUser(@NotNull AttachRoleDto dto) {
        validator.validateOnAttach(dto);
        if (repository.call(dto, "attachRole", Types.BOOLEAN)) {
            return get(dto.getUserId());
        } else {
            throw new RuntimeException((String.format("could not attach roles to user with id '%s'", dto.getUserId())));
        }
    }

    @Override
    public User findByUserName(String userName) {
        User user = repository.find(UserCriteria.childBuilder().userName(userName).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with userName '%s' not found", userName));
            throw new RuntimeException(String.format("user with userName '%s' not found", userName));
        }

        return user;
    }

    @Override
    @Cacheable(key = "#root.methodName")
    public List<Role> getRoles(String userName) {
        User user = repository.find(UserCriteria.childBuilder().userName(userName).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with userName '%s' not found", userName));
            throw new RuntimeException(String.format("user with userName '%s' not found", userName));
        }

        return (List<Role>) user.getRoles();
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_READ)")
    public ResponseEntity<DataDto<List<UserDto>>> getAll(UserCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }
}
