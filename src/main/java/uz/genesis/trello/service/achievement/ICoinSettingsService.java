package uz.genesis.trello.service.achievement;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.achievement.CoinSettingsCriteria;
import uz.genesis.trello.dto.achievement.CoinSettingsCrudDto;
import uz.genesis.trello.dto.achievement.CoinSettingsDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ICoinSettingsService extends IGenericCrudService<CoinSettingsDto, CoinSettingsCrudDto, CoinSettingsCrudDto, Long, CoinSettingsCriteria> {
    ResponseEntity<DataDto<CoinSettingsDto>> saveAndUpdateCoinSettings(CoinSettingsCrudDto dto);
}
