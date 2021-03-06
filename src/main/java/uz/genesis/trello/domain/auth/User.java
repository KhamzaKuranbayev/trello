package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.achievement.UserExpenseCoin;
import uz.genesis.trello.domain.achievement.UserIncomeCoin;
import uz.genesis.trello.domain.settings.Language;
import uz.genesis.trello.enums.UserStateType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "auth_users")
public class User extends Auditable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "email", unique = true)
    protected String email;

    @Column(name = "username", unique = true)
    protected String userName;

    @Column(name = "passwd")
    protected String password;
    @Column(name = "type_state")
    @Enumerated(EnumType.ORDINAL)
    protected UserStateType state;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "auth_users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
//    @WhereJoinTable(clause = "is_active = 1")
    protected Collection<Role> roles;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "organization_id")
    private Long organizationId;
    @OneToOne
    @JoinColumn(name = "lang_id", referencedColumnName = "id")
    private Language language;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @Builder.Default
    private List<UserIncomeCoin> incomeCoins = new ArrayList<>(); //do not add to dto

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @Builder.Default
    private List<UserExpenseCoin> expenseCoins = new ArrayList<>(); //do not add to dto
}
