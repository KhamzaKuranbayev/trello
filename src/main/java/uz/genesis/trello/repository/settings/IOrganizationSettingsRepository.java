package uz.genesis.trello.repository.settings;

import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.domain.settings.OrganizationSettings;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IOrganizationSettingsRepository extends IGenericCrudRepository<OrganizationSettings, OrganizationSettingsCriteria> {
}
