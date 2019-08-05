package uz.genesis.trello.service.file;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.file.FileHandoutCriteria;
import uz.genesis.trello.domain.files.FileHandout;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.FileHandoutCreateDto;
import uz.genesis.trello.dto.file.FileHandoutDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.file.FileHandoutMapper;
import uz.genesis.trello.repository.file.IFileHandoutRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.file.FileHandoutValidator;

@Service
public class FileHandoutService extends AbstractCrudService<FileHandoutDto, FileHandoutCreateDto, GenericCrudDto, FileHandoutCriteria, IFileHandoutRepository> implements IFileHandoutService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final FileHandoutMapper mapper;
    private final FileHandoutValidator validator;

    @Autowired
    public FileHandoutService(IFileHandoutRepository repository, BaseUtils utils, IErrorRepository errorRepository, FileHandoutMapper mapper, FileHandoutValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.validator = validator;
    }


    @Override
    public Long createFileHandout(FileHandoutCreateDto dto) {
        FileHandout handout = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(handout);
        handout.setId(repository.create(dto, "createFileHandout"));
        if (utils.isEmpty(handout.getId())) {
            logger.error(String.format("Non FileHandoutCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(FileHandout.class)));
        }

        return handout.getId();
    }


}
