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
@Table(name = "main_project_task_check_list_group")
public class CheckListGroup extends Auditable {

    private String name;

    @Column(name = "task_id")
    private Long taskId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "check_list_group_id")
    @Builder.Default
    private List<TaskCheckList> checkList = new ArrayList<>();
}
