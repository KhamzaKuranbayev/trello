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
@Table(name = "main_project_task_spent_time")
public class TaskSpentTime extends Auditable {

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name = "time_in_minute")
    private Integer timeMinute;
}
