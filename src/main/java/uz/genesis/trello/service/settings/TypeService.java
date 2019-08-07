package uz.genesis.trello.service.settings;

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
import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.SubTypeCreateDto;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.enums.Types;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.settings.TypeMapper;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.repository.settings.ITypeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.settings.TypeServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Created by 'javokhir' on 01/07/2019
 */
@Service
@CacheConfig(cacheNames = "types")
public class TypeService extends AbstractCrudService<TypeDto, TypeCreateDto, TypeUpdateDto, TypeCriteria, ITypeRepository> implements ITypeService {

    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TypeMapper mapper;
    private final TypeServiceValidator validator;

    @Autowired
    public TypeService(ITypeRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, TypeMapper mapper, TypeServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    @CacheEvict(allEntries = true, cacheNames = {"types", "projectColumns"})
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TYPE_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TypeCreateDto dto) {
        Type type = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(type);
        type.setId(repository.create(dto, "createType"));
        if (utils.isEmpty(type.getId())) {
            logger.error(String.format("Non TypeCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Type.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(type)), HttpStatus.CREATED);
    }

    @Override
    @Cacheable(key = "#root.methodName + #id")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TYPE_READ)")
    public ResponseEntity<DataDto<TypeDto>> get(Long id) {
        Type type = repository.find(TypeCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(type)) {
            logger.error(String.format("type with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Type.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(type)), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"types", "projectColumns"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TYPE_UPDATE)")
    public ResponseEntity<DataDto<TypeDto>> update(@NotNull TypeUpdateDto dto) {

        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));
        if (repository.update(dto, "updateType")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Type.class, dto.getId())));
        }
    }


    @Override
    @CacheEvict(value = {"types", "projectColumns"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TYPE_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteType")), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"types", "projectColumns"}, allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).SUB_TYPE_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> createSubType(SubTypeCreateDto dto) {
        Type type = mapper.fromSubTypeCreaeteDto(dto);
        validator.validateOnSubTypeCreate(dto);
        try {
            type.setId(repository.create(dto, "createSubType"));
        } catch (Exception ex) {
            logger.error(ex);
            logger.error(String.format(" dto '%s' ", dto.toString()));
            throw new RuntimeException(ex);
        }
        if (utils.isEmpty(type.getId())) {
            logger.error(String.format("Non SubTypeCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Type.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(type)), HttpStatus.CREATED);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.toString()")
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TYPE_READ)")
    public ResponseEntity<DataDto<List<TypeDto>>> getAll(TypeCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    public Long getIdByValue(Types type) {
        FunctionParam value = new FunctionParam(type.name(), java.sql.Types.VARCHAR);
        return repository.call(Collections.singletonList(value), "getIdByType", java.sql.Types.BIGINT);
    }
}
