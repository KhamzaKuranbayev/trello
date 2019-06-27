package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.UserMapper;
import uz.genesis.trello.repository.auth.IUserRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.UserServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Service
public class UserService extends AbstractCrudService<UserDto, UserCreateDto, UserUpdateDto, UserCriteria, IUserRepository> implements IUserService {

    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private UserMapper mapper;
    private GenericMapper genericMapper;
    private UserServiceValidator validator;

    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

    public UserService(IUserRepository repository, BaseUtils utils, UserServiceValidator validator) {
        super(repository, utils);
        this.mapper = Mappers.getMapper(UserMapper.class);
        this.genericMapper = Mappers.getMapper(GenericMapper.class);
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> get(Long id) {
        User user = repository.find(UserCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("user with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataDto<>(UserDto.childBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .roles(user.getRoles().stream().map(t -> RoleDto.childBuilder()
                        .roleName(t.getRoleName())
                        .permissions(t.getPermission().stream().map(p -> PermissionDto.childBuilder()
                                .name(p.getName()).build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()))
                .build()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserCreateDto dto) {
        User user = mapper.fromCreateDto(dto);

        validator.validateOnCreate(user);

        user.setPassword(oauthClientPasswordEncoder.encode(dto.getPassword()));


        try {
            user = repository.save(user);
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
}
