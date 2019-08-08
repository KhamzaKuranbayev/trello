package uz.genesis.trello.mapper.organization;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.dto.organization.OrganizationUserDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface OrganizationUserMapper {

    List<OrganizationUserDto> toDto(List<Organization> organizationList);

    OrganizationUserDto toDto(Organization organization);
}
