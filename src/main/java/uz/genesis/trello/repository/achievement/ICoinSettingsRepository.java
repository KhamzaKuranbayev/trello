package uz.genesis.trello.repository.achievement;

import uz.genesis.trello.criterias.achievement.CoinSettingsCriteria;
import uz.genesis.trello.domain.achievement.CoinSettings;
import uz.genesis.trello.repository.IGenericCrudRepository;


public interface ICoinSettingsRepository extends IGenericCrudRepository<CoinSettings, CoinSettingsCriteria> {
}
