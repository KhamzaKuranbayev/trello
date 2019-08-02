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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;
    private final IOtpHelperService otpHelperService;

    @Autowired
    public EmailService(JavaMailSender mailSender, @Qualifier("getFreeMarkerConfiguration") Configuration freemarkerConfig, IOtpHelperService otpHelperService) {
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.otpHelperService = otpHelperService;
    }

    @Override
    public void send(User user) throws MessagingException, IOException, TemplateException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


        String otpCode = otpHelperService.generateOpt(user);
        HashMap<String, Object> model = new HashMap<>();
        model.put("name", user.getUserName());
        model.put("code", otpCode);

        Template t = freemarkerConfig.getTemplate("mail-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setFrom("no-reply@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Verification code");
        helper.setText(html, true);

        mailSender.send(message);
    }
}
