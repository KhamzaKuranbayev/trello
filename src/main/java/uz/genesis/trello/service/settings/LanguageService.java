package uz.genesis.trello.service.settings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.domain.settings.Language;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.LanguageDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.settings.LanguageMapper;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.repository.settings.ILanguageRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;

import java.util.List;

@Service
public class LanguageService extends AbstractCrudService<LanguageDto, GenericCrudDto, GenericCrudDto, LanguageCriteria, ILanguageRepository> implements ILanguageService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final LanguageMapper mapper;


    @Autowired
    public LanguageService(ILanguageRepository repository, BaseUtils utils, IErrorRepository errorRepository, LanguageMapper mapper) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).LANGUAGE_READ)")
    public ResponseEntity<DataDto<LanguageDto>> get(Long id) {
        Language language = repository.find(LanguageCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(language)) {
            logger.error(String.format("language with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Language.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(language)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).LANGUAGE_READ)")
    public ResponseEntity<DataDto<List<LanguageDto>>> getAll(LanguageCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
