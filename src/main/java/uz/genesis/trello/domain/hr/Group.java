package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.organization.Organization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'javokhir' on 08/07/2019
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hr_groups")
public class Group extends Auditable {

    @Column(unique = true)
    private String name;

    @Column(name = "is_watcher")
    private boolean watcher;

    @Column(name = "organization_id")
    private Long organizationId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    @Builder.Default
    private List<EmployeeGroup> employeeGroups = new ArrayList<>();
}
