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
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.AttachPermissionDto;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.RoleMapper;
import uz.genesis.trello.repository.auth.RoleRepository;
import uz.genesis.trello.repository.auth.UserRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.RoleServiceValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"roles"})
public class RoleService extends AbstractCrudService<RoleDto, RoleCreateDto, RoleUpdateDto, RoleCriteria, RoleRepository> implements IRoleService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final RoleMapper mapper;
    private final GenericMapper genericMapper;
    private final RoleServiceValidator validator;
    private final UserRepository userRepository;

    public RoleService(RoleRepository repository, BaseUtils utils, IErrorRepository errorRepository, RoleMapper mapper, GenericMapper genericMapper, RoleServiceValidator validator, UserRepository userRepository) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.userRepository = userRepository;
    }


    @Override
    @CacheEvict(value = {"users", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).ROLE_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull RoleCreateDto dto) {
        Role role = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(role);
        role.setId(repository.create(dto, "createRole"));
        if (utils.isEmpty(role.getId())) {
            logger.error(String.format("Non RoleCreatedDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Role.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(role)), HttpStatus.CREATED);
    }

    @Override
    @Cacheable(key = "#root.methodName + #id")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).ROLE_READ)")
    public ResponseEntity<DataDto<RoleDto>> get(Long id) {
        Role role = repository.find(RoleCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(role)) {
            logger.error(String.format("role with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Role.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(role)), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"users", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).ROLE_UPDATE)")
    public ResponseEntity<DataDto<RoleDto>> update(@NotNull RoleUpdateDto dto) {

        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));
        if (repository.update(dto, "updateRole")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Role.class, dto.getId())));
        }
    }


    @Override
    @CacheEvict(value = {"users", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).ROLE_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteRole")), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.toString()")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).ROLE_READ)")
    public ResponseEntity<DataDto<List<RoleDto>>> getAll(RoleCriteria criteria) {
        List<Role> roles = repository.findAll(criteria);
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(roles), total), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"users", "roles"}, allEntries = true)
    public ResponseEntity<DataDto<RoleDto>> attachPermissionsToRole(AttachPermissionDto dto) {
        validator.validateOnAttach(dto);
        if (repository.call(dto, "attachpermissiontorole", Types.BOOLEAN)) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.COULD_NOT_ATTACH, utils.toErrorParams("permissions", "role", dto.getId())));
        }
    }
}
