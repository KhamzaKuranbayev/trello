package uz.genesis.trello.controller.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.CertificateDto;
import uz.genesis.trello.service.certificate.ICertiicateService;
import uz.genesis.trello.service.settings.IOrganizationSettingsService;


@RestController
public class CertificateController extends ApiController<ICertiicateService> {

    private final IOrganizationSettingsService organizationSettingsService;
    @Autowired
    public CertificateController(ICertiicateService service, IOrganizationSettingsService organizationSettingsService) {
        super(service);
        this.organizationSettingsService = organizationSettingsService;
    }

    @RequestMapping(value = API_PATH + V_1 + "/certificate/publicKey", method = RequestMethod.GET)
    public ResponseEntity<DataDto<String>> getPublicKey() {
            return service.getPublicKey();
    }

    @RequestMapping(value = API_PATH + V_1 + "/certificate/setKey", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Boolean>> setPrivateKey(@RequestBody CertificateDto privateKey) {
        return organizationSettingsService.setCertificate(privateKey);
    }

}
