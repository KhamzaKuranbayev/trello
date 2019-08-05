package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.PermissionCriteria;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.PermissionMapper;
import uz.genesis.trello.repository.auth.PermissionRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.PermissionServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = "permissions")
public class PermissionService extends AbstractCrudService<PermissionDto, PermissionCreateDto, PermissionUpdateDto, PermissionCriteria, PermissionRepository> implements IPermissionService {

    protected final Log logger = LogFactory.getLog(getClass());

    private final PermissionMapper mapper;
    private final GenericMapper genericMapper;
    private final PermissionServiceValidator validator;

    @Autowired
    public PermissionService(PermissionRepository repository, BaseUtils utils, IErrorRepository errorRepository, PermissionMapper mapper, GenericMapper genericMapper, PermissionServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }


    @Override
    @CacheEvict(value = {"users", "permissions", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PERMISSION_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull PermissionCreateDto dto) {
        Permission permission = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(permission);
        permission.setId(repository.create(dto, "createPermission"));
        if (utils.isEmpty(permission.getId())) {
            logger.error(String.format("Non PermissionCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Permission.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(permission)), HttpStatus.CREATED);
    }

    @Override
    @Cacheable(key = "#root.methodName + #id")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PERMISSION_READ)")
    public ResponseEntity<DataDto<PermissionDto>> get(Long id) {
        Permission permission = repository.find(PermissionCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(permission)) {
            logger.error(String.format("permission with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Permission.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(permission)), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"users", "permissions", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PERMISSION_UPDATE)")
    public ResponseEntity<DataDto<PermissionDto>> update(@NotNull PermissionUpdateDto dto) {

        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));
        if (repository.update(dto, "updatePermission")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Permission.class, dto.getId())));
        }
    }

    @Override
    @CacheEvict(value = {"users", "permissions", "roles"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PERMISSION_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deletePermission")), HttpStatus.OK);

    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.toString()")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PERMISSION_READ)")
    public ResponseEntity<DataDto<List<PermissionDto>>> getAll(PermissionCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
