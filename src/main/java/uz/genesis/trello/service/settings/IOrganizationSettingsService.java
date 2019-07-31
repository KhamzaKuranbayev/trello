package uz.genesis.trello.service.settings;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsCreateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IOrganizationSettingsService extends IGenericCrudService<OrganizationSettingsDto, OrganizationSettingsCreateDto, OrganizationSettingsUpdateDto, Long, OrganizationSettingsCriteria> {
    OrganizationSettingsDto getOrganizationSettings(OrganizationSettingsCriteria criteria);
    Integer getCurrentUserCount(Long organizationId);
    ResponseEntity<DataDto<Boolean>> setCertificate(String privateKey);
}
