package uz.genesis.trello.service.file;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.file.ResourceFileCriteria;
import uz.genesis.trello.domain.files.ResourceFile;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.file.ResourceFileCreateDto;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.mapper.file.ResourceFileMapper;
import uz.genesis.trello.repository.file.IResourceFileRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;

@Service
public class ResourceFileService extends AbstractCrudService<ResourceFileDto, ResourceFileCreateDto, GenericCrudDto, ResourceFileCriteria, IResourceFileRepository> implements IResourceFileService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final ResourceFileMapper mapper;

    public ResourceFileService(IResourceFileRepository repository, BaseUtils utils, ResourceFileMapper mapper) {
        super(repository, utils);
        this.mapper = mapper;
    }

    @Override
    public ResourceFileDto createFile(ResourceFileCreateDto dto) {
        ResourceFile file = mapper.fromCreateDto(dto);
//        validator.validateDomainOnCreate(project);
        file.setId(repository.create(dto, "createResourceFile"));
        if (utils.isEmpty(file.getId())) {
            logger.error(String.format("Non ResourceFileCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ResourceFileCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return mapper.toDto(repository.find(file.getId()));
    }
}
