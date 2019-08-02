package uz.genesis.trello.utils.otp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpMessage implements Serializable {
    public OtpSms sms;
    private String recipient;

    public OtpMessage(String recipient, String originator, String text) {
        this.recipient = recipient;
        sms = new OtpSms(originator, new OtpMessageContent(text));
    }
}