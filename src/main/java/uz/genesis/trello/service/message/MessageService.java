package uz.genesis.trello.service.message;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.genesis.trello.domain.auth.User;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class MessageService implements IMessageService {

    private final EmailService emailService;

    @Autowired
    public MessageService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendMessage(User user) throws MessagingException, IOException, TemplateException {
        if (user.getPhoneNumber().isEmpty()){
            //todo sms service logic
        } else {
            emailService.send(user);
        }
    }
}
