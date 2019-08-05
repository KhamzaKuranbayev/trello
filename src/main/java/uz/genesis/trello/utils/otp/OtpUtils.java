package uz.genesis.trello.utils.otp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OtpUtils {

    /**
     * PlayMobile "send.sms" request body
     *
     * @param phoneNumber recipient phone number
     * @param originator middleware originator
     * @param otp generated one time password
     * @return ObjectNode
     */
    public ObjectNode smsServiceSendSms(String phoneNumber, String originator, String otp){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode messages = objectMapper.createArrayNode();
        ObjectNode content = objectMapper.createObjectNode();
        ObjectNode otpMessage = objectMapper.createObjectNode();
        ObjectNode otpSms = objectMapper.createObjectNode();
        ObjectNode otpMessageContent = objectMapper.createObjectNode();
        Date messageDate = new Date();

        otpMessageContent.put("text", otp);

        otpSms.put("originator", originator);
        otpSms.putPOJO("content", otpMessageContent);

        otpMessage.put("recipient", phoneNumber);
        otpMessage.put("message-id", messageDate.getTime());
        otpMessage.putPOJO("sms", otpSms);

        messages.addPOJO(otpMessage);
        content.putPOJO("messages", messages);
        return content;
    }
}
