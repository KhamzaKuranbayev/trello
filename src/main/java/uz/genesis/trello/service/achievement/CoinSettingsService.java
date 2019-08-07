package uz.genesis.trello.service.achievement;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.achievement.CoinSettingsCriteria;
import uz.genesis.trello.domain.achievement.CoinSettings;
import uz.genesis.trello.dto.achievement.CoinSettingsCrudDto;
import uz.genesis.trello.dto.achievement.CoinSettingsDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.achievement.CoinSettingsMapper;
import uz.genesis.trello.repository.achievement.ICoinSettingsRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.achievement.CoinSettingsValidator;

import java.sql.Types;
import java.util.List;

@Service
public class CoinSettingsService extends AbstractCrudService<CoinSettingsDto, CoinSettingsCrudDto, CoinSettingsCrudDto, CoinSettingsCriteria, ICoinSettingsRepository> implements ICoinSettingsService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final CoinSettingsValidator validator;
    private final CoinSettingsMapper mapper;


    @Autowired
    public CoinSettingsService(ICoinSettingsRepository repository, BaseUtils utils, IErrorRepository errorRepository, CoinSettingsValidator validator, CoinSettingsMapper mapper) {
        super(repository, utils, errorRepository);
        this.validator = validator;
        this.mapper = mapper;
    }


    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).COIN_CREATE_AND_UPDATE)")
    public ResponseEntity<DataDto<CoinSettingsDto>> saveAndUpdateCoinSettings(CoinSettingsCrudDto dto) {
        CoinSettings coinSettings = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(coinSettings);
        coinSettings.setId(repository.call(dto, "achievementCoinSetting", Types.BIGINT));
        if (utils.isEmpty(coinSettings.getId())) {
            logger.error(String.format("Non CoinSettingsCrudDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(CoinSettings.class)));
        }
        return get(coinSettings.getId());
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).COIN_READ)")
    public ResponseEntity<DataDto<CoinSettingsDto>> get(Long id) {
        CoinSettings coinSettings = repository.find(CoinSettingsCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(coinSettings)) {
            logger.error(String.format("coinSettings with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(CoinSettings.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(coinSettings)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).COIN_READ)")
    public ResponseEntity<DataDto<List<CoinSettingsDto>>> getAll(CoinSettingsCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

}

