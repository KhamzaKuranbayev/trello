package uz.genesis.trello.service.message;

import freemarker.template.TemplateException;
import uz.genesis.trello.dto.message.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IEmailService {
    void send(Mail mail) throws MessagingException, IOException, TemplateException;
}
