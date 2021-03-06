package uz.genesis.trello.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.organization.OrganizationCreateOtpDto;
import uz.genesis.trello.dto.organization.OrganizationOtpConfirmDto;
import uz.genesis.trello.dto.organization.OrganizationUserDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.organization.IOrganizationService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class OrganizationController extends ApiController<IOrganizationService> {
    @Autowired
    public OrganizationController(IOrganizationService service) {
        super(service);
    }

    @RequestMapping(value = ORGANIZATION_OTP_URL, method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> createOrganizationWithOtp(@RequestBody OrganizationCreateOtpDto dto, HttpServletResponse httpServletResponse) {
        return service.checkByEmail(dto, httpServletResponse);
    }

    @RequestMapping(value = ORGANIZATION_OTP_CONFIRM_URL, method = RequestMethod.POST)
    public ResponseEntity<DataDto<Boolean>> confirmOtp(@RequestBody OrganizationOtpConfirmDto dto) {
        return service.authOtpConfirm(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/organizations/user", method = RequestMethod.POST)
    public ResponseEntity<DataDto<OrganizationUserDto>> updateOrganizationUser(@RequestBody OrganizationUserDto dto) {
        return service.updateOrganizationUser(dto);
    }
}
