package uz.genesis.trello.domain.main;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "main_project_tasks")
public class Task extends Auditable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "column_id")
    private Long columnId;

    @Column(columnDefinition = "TEXT", unique = true)
    private String name;

    @Column(columnDefinition = "TEXT", unique = true)
    private String description;

    @CreationTimestamp
    @Column(name = "dead_line", columnDefinition = "TIMESTAMP default null")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime deadLine;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskTag> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskMember> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskCheckList> checkList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskComment> comments = new ArrayList<>();

}
