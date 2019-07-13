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
@Table(name = "main_project_columns")
public class ProjectColumn extends Auditable {

    private String name;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "project_id")
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_column_type", nullable = false, referencedColumnName = "id")
    private Type columnType;

    @Column(name = "ordering", columnDefinition = "NUMERIC default null")
    private Integer ordering;
}
