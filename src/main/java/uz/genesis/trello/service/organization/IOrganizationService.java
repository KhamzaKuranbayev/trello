package uz.genesis.trello.service.organization;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.organization.OrganizationCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.organization.OrganizationOtpConfirmDto;
import uz.genesis.trello.dto.organization.OrganizationUserDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericService;

public interface IOrganizationService extends IGenericService<OrganizationUserDto, Long, OrganizationCriteria> {
    ResponseEntity<DataDto<GenericDto>> checkByEmail(String mail);

    ResponseEntity<DataDto<Boolean>> authOtpConfirm(OrganizationOtpConfirmDto dto);

    ResponseEntity<DataDto<OrganizationUserDto>> updateOrganizationUser(OrganizationUserDto dto);
}
