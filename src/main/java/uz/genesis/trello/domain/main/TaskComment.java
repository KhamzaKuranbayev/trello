package uz.genesis.trello.domain.main;


import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_project_task_comments")
public class TaskComment extends Auditable {

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText;
}
