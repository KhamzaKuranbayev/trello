package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_project_task_actions")
public class TaskAction extends Auditable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_column_id")
    private Long projectColumnId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(columnDefinition = "TEXT")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_type", nullable = false, referencedColumnName = "id")
    private Type actionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_category_type", nullable = false, referencedColumnName = "id")
    private Type actionCategoryType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_type", nullable = false, referencedColumnName = "id")
    private Type activityType;

    @Column(name = "text_params", columnDefinition = "TEXT")
    private String textParams;

}
