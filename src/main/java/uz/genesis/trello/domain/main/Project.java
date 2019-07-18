package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_projects")
public class Project extends Auditable {

    private String name;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager", referencedColumnName = "user_id")
    private Employee manager;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ProjectMember> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ProjectColumn> columns = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_type", nullable = false, referencedColumnName = "id")
    private Type projectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_level_type", referencedColumnName = "id")
    private Type projectLevelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_priority_type", referencedColumnName = "id")
    private Type projectPriorityType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ProjectTag> tags = new ArrayList<>();
}
