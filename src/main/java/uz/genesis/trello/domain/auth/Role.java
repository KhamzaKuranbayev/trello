package uz.genesis.trello.domain.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_roles")
public class Role extends Auditable implements GrantedAuthority {

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "auth_roles_permissions", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
//    @WhereJoinTable(clause = "is_active = 1")
    private Collection<Permission> permissions;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
