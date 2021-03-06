package uz.genesis.trello.domain.organization;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.achievement.CoinSettings;
import uz.genesis.trello.domain.achievement.UserIncomeCoin;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.files.Background;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.domain.main.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "organizations")
public class Organization extends Auditable {

    private String name;

    @Column(unique = true)
    private String prefix;

    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<Group> groups = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<Project> projects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<Role> roles = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<CoinSettings> coinSettingsList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    @Builder.Default
    private List<Background> backgrounds = new ArrayList<>();
}
