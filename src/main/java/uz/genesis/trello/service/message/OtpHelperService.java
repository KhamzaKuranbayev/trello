package uz.genesis.trello.service.message;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.exception.GenericRuntimeException;
import uz.genesis.trello.property.PlayMobileProperties;
import uz.genesis.trello.repository.auth.IUserOtpRepository;
import uz.genesis.trello.repository.auth.IUserRepository;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.otp.OtpUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static uz.genesis.trello.enums.Types.AUTH_OTP_TYPE_PHONE;

@Service
public class OtpHelperService implements IOtpHelperService {

    private static String SMS_SERVICE_URL;
    private static String SMS_SERVICE_AUTH;
    private static String SMS_SERVICE_ORIGINATOR;
    private final Log logger = LogFactory.getLog(getClass());
    private final IUserOtpRepository userOtpRepository;
    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;
    private final OtpUtils otpUtils;
    private final IUserRepository userRepository;
    private final IErrorRepository errorRepository;
    private final BaseUtils baseUtils;

    @Autowired
    public OtpHelperService(IUserOtpRepository userOtpRepository, PlayMobileProperties playMobileProperties, JavaMailSender mailSender, @Qualifier("getFreeMarkerConfiguration") Configuration freemarkerConfig, OtpUtils otpUtils, IUserRepository userRepository, IErrorRepository errorRepository, BaseUtils baseUtils) {

        SMS_SERVICE_URL = "http://" + playMobileProperties.getIp() + ":" + playMobileProperties.getPort() + playMobileProperties.getPath();
        SMS_SERVICE_AUTH = playMobileProperties.getToken();
        SMS_SERVICE_ORIGINATOR = playMobileProperties.getOriginator();
        this.userOtpRepository = userOtpRepository;
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.otpUtils = otpUtils;
        this.userRepository = userRepository;
        this.errorRepository = errorRepository;
        this.baseUtils = baseUtils;
    }

    @Override
    public void sendOtp(String username) {
        User user = userRepository.find(UserCriteria.childBuilder().userName(username).forAuthenticate(true).build());

        String otp = generateOpt(username);

        if (!baseUtils.isEmpty(user.getPhoneNumber())) {
            sendSms(user, otp);
        } else if (!baseUtils.isEmpty(user.getEmail())) {
            try {
                sendMail(user, otp);
            } catch (TemplateException | IOException | MessagingException e) {
                logger.error(e);
                throw new GenericRuntimeException(e.getMessage(), e);
            }
        } else {
            throw new GenericRuntimeException("user email or phone number are required.");
        }
    }

    @Override
    public boolean confirmOtp(String username, String key) {
        List<FunctionParam> params = new ArrayList<>();
        params.add(FunctionParam.builder().param(username).paramType(Types.VARCHAR).build());
        params.add(FunctionParam.builder().param(key).paramType(Types.VARCHAR).build());
        return userOtpRepository.call(params, "checkuserotpcode", Types.BOOLEAN);
    }

    private void sendSms(User user, String otp) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(SMS_SERVICE_URL);
            String basic = new String(Base64.encodeBase64(SMS_SERVICE_AUTH.getBytes()));
            String reqBody = otpUtils.smsServiceSendSms("+" + user.getPhoneNumber(), SMS_SERVICE_ORIGINATOR, otp).toString();

            httpPost.setEntity(new StringEntity(reqBody, ContentType.APPLICATION_JSON));
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + basic);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            HttpResponse response = httpClient.execute(httpPost);
            String str = EntityUtils.toString(response.getEntity());
            if (!str.equalsIgnoreCase("Request is received")) {
                logger.error(str);
                throw new GenericRuntimeException(str);
            }
        } catch (IOException e) {
            logger.error(e);
            throw new GenericRuntimeException(e.getMessage(), e);
        }
    }

    private void sendMail(User user, String otp) throws MessagingException, IOException, TemplateException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        HashMap<String, Object> model = new HashMap<>();
        model.put("name", user.getUserName());
        model.put("code", otp);

        Template t = freemarkerConfig.getTemplate("mail-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setFrom("no-reply@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Verification code");
        helper.setText(html, true);

        mailSender.send(message);
    }

    private String generateOpt(String username) {
        List<FunctionParam> params = new ArrayList<>();
        params.add(FunctionParam
                .builder()
                .param(AUTH_OTP_TYPE_PHONE.parent)
                .paramType(Types.VARCHAR)
                .build());

        Long typeId = userOtpRepository.call(params, "getidbytype", Types.BIGINT);
        List<FunctionParam> userOtpParams = new ArrayList<>();
        userOtpParams.add(FunctionParam.builder().param(username).paramType(Types.VARCHAR).build());
        userOtpParams.add(FunctionParam.builder().param(typeId).paramType(Types.BIGINT).build());
        return userOtpRepository.call(userOtpParams, "createUserOtp", Types.VARCHAR);
    }
}
