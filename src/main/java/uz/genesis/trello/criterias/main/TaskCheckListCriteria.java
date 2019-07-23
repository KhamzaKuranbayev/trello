package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCheckListCriteria extends GenericCriteria {
    private Long checkListGroupId;
    private String text;
    private Boolean projectDetail;
    private Long taskId;
    private Boolean checked;

    @Builder(builderMethodName = "childBuilder")
    public TaskCheckListCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long checkListGroupId, String text, Boolean checked, Boolean projectDetail, Long taskId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.text = text;
        this.projectDetail = projectDetail;
        this.checkListGroupId = checkListGroupId;
        this.checked = checked;
        this.taskId = taskId;
    }
}
