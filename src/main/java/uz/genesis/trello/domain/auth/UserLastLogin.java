package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.utils.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_user_last_login")
public class UserLastLogin extends Auditable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "login_at", columnDefinition = "TIMESTAMP default NOW()")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime loginAt;

    @Column(name = "ip_address")
    private String ipAddress;

}
