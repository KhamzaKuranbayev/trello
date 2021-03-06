package uz.genesis.trello.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "service.prop")
public class ServerProperties {

    private String port;

    private String ip;

    private String url;
}
