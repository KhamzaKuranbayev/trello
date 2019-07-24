package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskActionCriteria extends GenericCriteria {
    private Long projectId;
    private Long projectColumnId;
    private Long taskId;
    private String name;
    private String textParams;


    @Builder(builderMethodName = "childBuilder")
    public TaskActionCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long projectId, Long projectColumnId, Long taskId, String name, String textParams) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.projectId = projectId;
        this.projectColumnId = projectColumnId;
        this.taskId = taskId;
        this.name = name;
        this.textParams = textParams;
    }
}
