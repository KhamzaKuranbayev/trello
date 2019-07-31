package uz.genesis.trello.service.certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.pkcs.PKCSChecker;

@Service
public class CertificateService implements ICertiicateService {

    protected final Log logger = LogFactory.getLog(getClass());
    private final PKCSChecker pkcsChecker;
    private final BaseUtils utils;

    @Autowired
    public CertificateService(PKCSChecker pkcsChecker, BaseUtils utils) {
        this.pkcsChecker = pkcsChecker;
        this.utils = utils;
    }

    @Override
    public ResponseEntity<DataDto<String>> getPublicKey() {
        String publicKey = pkcsChecker.generateUniquePublicKey();
        if(utils.isEmpty(publicKey)){
            logger.error("Could not generate public key");
            throw new RuntimeException("Could not generate public key");
        }
        return new ResponseEntity<>(new DataDto<>(pkcsChecker.generateUniquePublicKey()), HttpStatus.OK);
    }
}
