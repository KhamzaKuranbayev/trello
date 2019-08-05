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
import uz.genesis.trello.mapper.file.FileHandoutMapper;
import uz.genesis.trello.repository.file.IFileHandoutRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

@Service
public class FileHandoutService extends AbstractCrudService<FileHandoutDto, FileHandoutCreateDto, GenericCrudDto, FileHandoutCriteria, IFileHandoutRepository> implements IFileHandoutService {
    private final FileHandoutMapper mapper;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public FileHandoutService(IFileHandoutRepository repository, BaseUtils utils, IErrorRepository errorRepository, FileHandoutMapper mapper) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
    }

    @Override
    public Long createFileHandout(FileHandoutCreateDto dto){
        FileHandout handout = mapper.fromCreateDto(dto);
        handout.setId(repository.create(dto, "createFileHandout"));
        if (utils.isEmpty(handout.getId())) {
            logger.error(String.format("Non FileHandoutCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non FileHandoutCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return handout.getId();
    }


}
