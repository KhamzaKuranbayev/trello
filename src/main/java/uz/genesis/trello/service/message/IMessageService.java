package uz.genesis.trello.service.message;

import freemarker.template.TemplateException;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.service.IAbstractService;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IMessageService extends IAbstractService {
    void sendMessage(User user) throws MessagingException, IOException, TemplateException;
}
