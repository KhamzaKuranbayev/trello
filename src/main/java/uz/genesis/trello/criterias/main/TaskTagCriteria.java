package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagCriteria extends GenericCriteria {

    private Long projectTagId;
    private Long taskId;

    @Builder(builderMethodName = "childBuilder")
    public TaskTagCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long projectTagId, Long taskId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.projectTagId = projectTagId;
        this.taskId = taskId;
    }
}
