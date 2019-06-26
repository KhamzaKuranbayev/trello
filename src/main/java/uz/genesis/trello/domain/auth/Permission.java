package uz.genesis.trello.domain.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_permissions")
public class Permission  extends Auditable implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

}
