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
@Table(name = "main_project_task_tags")
public class TaskTag extends Auditable {

    @Column(name = "project_tag_id")
    private Long projectTagId;

    @Column(name = "task_id")
    private Long taskId;
}
