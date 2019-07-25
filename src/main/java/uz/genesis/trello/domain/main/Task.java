package uz.genesis.trello.domain.main;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.achievement.UserIncomeCoin;
import uz.genesis.trello.domain.settings.Type;
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

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "start_at", columnDefinition = "TIMESTAMP default now()")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startAt;

    @CreationTimestamp
    @Column(name = "dead_line", columnDefinition = "TIMESTAMP default null")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime deadLine;

    @Column(name = "ordering", columnDefinition = "NUMERIC default null")
    private Integer ordering;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskTag> tags = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskMember> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<CheckListGroup> checkListGroups = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<TaskComment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_level_type", referencedColumnName = "id")
    private Type taskLevelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_priority_type", referencedColumnName = "id")
    private Type taskPriorityType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<UserIncomeCoin> incomeCoins = new ArrayList<>(); //do not add to dto
}
