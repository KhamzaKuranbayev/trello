package uz.genesis.trello.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "service.sms")
public class PlayMobileProperties {

    private String ip;

    private String port;

    private String path;

    private String token;

    private String originator;

}
