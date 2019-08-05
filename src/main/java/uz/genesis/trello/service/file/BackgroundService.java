package uz.genesis.trello.service.file;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.file.BackgroundCriteria;
import uz.genesis.trello.domain.files.Background;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.file.BackgroundMapper;
import uz.genesis.trello.repository.file.IBackgrounRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.file.BackgroundServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class BackgroundService extends AbstractCrudService<BackgroundDto, BackgroundCreateDto, BackgroundUpdateDto, BackgroundCriteria, IBackgrounRepository> implements IBackgroundService {
    private final BackgroundServiceValidator validator;
    protected final Log logger = LogFactory.getLog(getClass());
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
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull BackgroundCreateDto dto) {
        Background background = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(background);
        background.setId(repository.create(dto, "createFileBackground"));
        if (utils.isEmpty(background.getId())) {
            logger.error(String.format("Non BackgroundCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non BackgroundCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(background)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<BackgroundDto>> update(@NotNull BackgroundUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateFileBackground")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update background with  id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteFileBackground")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete background with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<BackgroundDto>> get(Long id) {
        Background background = repository.find(id);
        if (utils.isEmpty(background)) {
            logger.error(String.format("background with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("background with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(background)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<BackgroundDto>>> getAll(BackgroundCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
