package uz.genesis.trello.service.settings;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.domain.settings.ErrorMessage;
import uz.genesis.trello.domain.settings.Language;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageDto;
import uz.genesis.trello.dto.settings.ErrorMessageTranslationCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.enums.LanguageType;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.settings.ErrorMessageMapper;
import uz.genesis.trello.repository.settings.IErrorMessageRepository;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.repository.settings.ILanguageRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.settings.ErrorMessageServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ErrorMessageService extends AbstractCrudService<ErrorMessageDto, ErrorMessageCreateDto, ErrorMessageUpdateDto, ErrorMessageCriteria, IErrorMessageRepository> implements IErrorMessageService {

    protected final Log logger = LogFactory.getLog(getClass());
    private final ErrorMessageMapper mapper;
    private final ILanguageRepository languageRepository;
    private final GenericMapper genericMapper;
    private final ErrorMessageServiceValidator validator;

    public ErrorMessageService(IErrorMessageRepository repository, BaseUtils utils, IErrorRepository errorRepository, ErrorMessageMapper mapper, ILanguageRepository languageRepository, GenericMapper genericMapper, ErrorMessageServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.languageRepository = languageRepository;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ErrorMessageCreateDto dto) {
        ErrorMessage errorMessage = mapper.fromCreateDto(dto);

        validator.validateDomainOnCreate(errorMessage);
        validator.validateForTranslations(dto.getTranslations());
        dto.setTranslations(fillTranslations(dto.getTranslations()));

        errorMessage.setId(repository.create(dto, "createErrorMessage"));
        if (utils.isEmpty(errorMessage.getId())) {
            logger.error(String.format("Non ErrorMessageCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(ErrorMessage.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(errorMessage)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ErrorMessageDto>> update(@NotNull ErrorMessageUpdateDto dto) {
        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));
        if (repository.update(dto, "updateErrorMessage")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(ErrorMessage.class, dto.getId())));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteErrorMessage")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ErrorMessageDto>> get(Long id) {
        ErrorMessage errorMessage = repository.find(ErrorMessageCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(errorMessage)) {
            logger.error(String.format("errorMessage with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(ErrorMessage.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(errorMessage)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ErrorMessageDto>>> getAll(ErrorMessageCriteria criteria) {
        List<ErrorMessage> errorMessageList = repository.findAll(criteria);
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(errorMessageList), total), HttpStatus.OK);
    }


    private List<ErrorMessageTranslationCreateDto> fillTranslations(List<ErrorMessageTranslationCreateDto> dtoList) {
        List<Language> languageList = languageRepository.findAll(LanguageCriteria.childBuilder().build());
        String ruMessage = "";

        for (ErrorMessageTranslationCreateDto errorMessageTranslationCreateDto : dtoList) {
            if (errorMessageTranslationCreateDto.getLangCode().equalsIgnoreCase(LanguageType.RU.name())) {
                ruMessage = errorMessageTranslationCreateDto.getName();
                break;
            }
        }

        for (Language language : languageList) {
            boolean isNotContained = true;
            for (ErrorMessageTranslationCreateDto dto : dtoList) {
                if (language.getCode().equalsIgnoreCase(dto.getLangCode())) {
                    isNotContained = false;
                    break;
                }
            }
            if (isNotContained) {
                ErrorMessageTranslationCreateDto newLanguageDto = new ErrorMessageTranslationCreateDto(ruMessage, language.getCode());
                dtoList.add(newLanguageDto);
            }
        }

        return dtoList;
    }


}
