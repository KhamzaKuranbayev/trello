package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.hr.Group;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ProjectMember> members = new ArrayList<>();
}