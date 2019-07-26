package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_project_task_check_list")
public class TaskCheckList extends Auditable {

    @Column(name = "check_list_group_id")
    private Long checkListGroupId;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_checked")
    private boolean checked;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "check_list_id")
    @Builder.Default
    private List<CheckListMember> members = new ArrayList<>();
}
