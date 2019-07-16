package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCheckListCriteria extends GenericCriteria {
    private Long taskId;
    private String text;
    private boolean checked;

    @Builder(builderMethodName = "childBuilder")
    public TaskCheckListCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long taskId, String text, boolean checked) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.taskId = taskId;
        this.text = text;
        this.checked = checked;
    }
}
