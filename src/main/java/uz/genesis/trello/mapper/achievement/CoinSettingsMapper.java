package uz.genesis.trello.mapper.achievement;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.achievement.CoinSettings;
import uz.genesis.trello.dto.achievement.CoinSettingsCrudDto;
import uz.genesis.trello.dto.achievement.CoinSettingsDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CoinSettingsMapper extends BaseMapper<CoinSettings, CoinSettingsDto, CoinSettingsCrudDto, CoinSettingsCrudDto> {
}
