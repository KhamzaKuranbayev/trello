package uz.genesis.trello.mapper.settings;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.settings.OrganizationSettings;
import uz.genesis.trello.dto.settings.OrganizationSettingsCreateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface OrganizationSettingsMapper extends BaseMapper<OrganizationSettings, OrganizationSettingsDto, OrganizationSettingsCreateDto, OrganizationSettingsUpdateDto> {
}
