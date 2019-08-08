package uz.genesis.trello.service.message;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IOtpHelperService {
    void sendEmailAuth(String email, String otp) throws MessagingException, IOException, TemplateException;

    boolean confirmOtp(String otpCode, String code, String methodName);

    void sendOtp(String username) throws MessagingException, IOException, TemplateException;

    String generateAuthOtp(String mail);
}
