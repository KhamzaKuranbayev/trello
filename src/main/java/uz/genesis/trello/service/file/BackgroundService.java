package uz.genesis.trello.service.file;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.file.BackgroundCriteria;
import uz.genesis.trello.domain.files.Background;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.file.BackgroundMapper;
import uz.genesis.trello.repository.file.IBackgrounRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.file.BackgroundServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class BackgroundService extends AbstractCrudService<BackgroundDto, BackgroundCreateDto, BackgroundUpdateDto, BackgroundCriteria, IBackgrounRepository> implements IBackgroundService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final BackgroundServiceValidator validator;
    private final BackgroundMapper mapper;
    private final GenericMapper genericMapper;

    @Autowired
    public BackgroundService(IBackgrounRepository repository, BaseUtils utils, IErrorRepository errorRepository, BackgroundServiceValidator validator, BackgroundMapper mapper, GenericMapper genericMapper) {
        super(repository, utils, errorRepository);
        this.validator = validator;
        this.mapper = mapper;
        this.genericMapper = genericMapper;
    }


    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).BACKGROUND_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull BackgroundCreateDto dto) {
        Background background = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(background);
        background.setId(repository.create(dto, "createFileBackground"));
        if (utils.isEmpty(background.getId())) {
            logger.error(String.format("Non BackgroundCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Background.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(background)), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).BACKGROUND_UPDATE)")
    public ResponseEntity<DataDto<BackgroundDto>> update(@NotNull BackgroundUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateFileBackground")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Background.class, dto.getId())));
        }
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).BACKGROUND_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteFileBackground")), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).BACKGROUND_READ)")
    public ResponseEntity<DataDto<BackgroundDto>> get(Long id) {
        Background background = repository.find(id);
        if (utils.isEmpty(background)) {
            logger.error(String.format("background with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Background.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(background)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).BACKGROUND_READ)")
    public ResponseEntity<DataDto<List<BackgroundDto>>> getAll(BackgroundCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
