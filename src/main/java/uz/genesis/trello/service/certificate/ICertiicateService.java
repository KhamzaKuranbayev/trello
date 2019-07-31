package uz.genesis.trello.service.certificate;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IAbstractService;

public interface ICertiicateService extends IAbstractService {
    ResponseEntity<DataDto<String>> getPublicKey();
}
