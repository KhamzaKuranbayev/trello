package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCriteria extends GenericCriteria {
    private String name;
    private Long projectId;
    private Long columnId;
    private String description;
    private String deadLine;

    @Builder(builderMethodName = "childBuilder")
    public TaskCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, Long projectId, Long columnId, String description, String deadLine) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.projectId = projectId;
        this.columnId = columnId;
        this.description = description;
        this.deadLine = deadLine;


    }
}
