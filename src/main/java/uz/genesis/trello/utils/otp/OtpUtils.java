package uz.genesis.trello.utils.otp;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class OtpUtils {

    public String generateMessage(){
        String plainCreds = "StopUzcard:S4#!oUz5cAr";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        return base64Creds;
    }
}
