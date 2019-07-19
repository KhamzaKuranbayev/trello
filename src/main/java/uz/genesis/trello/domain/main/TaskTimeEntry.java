package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_project_task_time_entries")
public class TaskTimeEntry extends Auditable {

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "employee_id")
    private Long employee_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private TaskTimeEntry parent;

    @Column(name = "entry_time", columnDefinition = "TIMESTAMP default NOW()")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime entry_time;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entry_type", nullable = false, referencedColumnName = "id")
    private Type entryType;

}
