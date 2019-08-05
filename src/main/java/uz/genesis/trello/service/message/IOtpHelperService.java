package uz.genesis.trello.service.message;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IOtpHelperService {
    boolean confirmOtp(String username, String code);
    void sendOtp(String username) throws MessagingException, IOException, TemplateException;
}
