package uz.genesis.trello.utils.validators.file;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.files.FileHandout;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.file.FileHandoutCreateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class FileHandoutValidator extends BaseCrudValidator<FileHandout, FileHandoutCreateDto, CrudDto> {
    public FileHandoutValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(FileHandout domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(FileHandout.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        } else if (utils.isEmpty(domain.getSourceType())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("sourceType", FileHandout.class)));
        } else if (utils.isEmpty(domain.getSourceTypeParent())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("sourceTypeParent", FileHandout.class)));
        } else if (utils.isEmpty(domain.getSourceId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("sourceId", FileHandout.class)));
        }
    }
}
