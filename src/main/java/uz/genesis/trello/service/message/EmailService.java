package uz.genesis.trello.service.message;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.message.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("getFreeMarkerConfiguration")
    @Autowired
    private Configuration freemarkerConfig;


    @Override
    public void send(User user) throws MessagingException, IOException, TemplateException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Map model = new HashMap();
        model.put("name", user.getUserName());
        model.put("code", "123456"); //TODO implement unique random code

        Template t = freemarkerConfig.getTemplate("mail-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setFrom("no-reply@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Verification code");
        helper.setText(html, true);

        mailSender.send(message);
    }
}
