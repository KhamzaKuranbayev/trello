package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_tries")
public class AuthTry extends Auditable {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "ip_address")
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_type", referencedColumnName = "id")
    private Type resultType;
}
