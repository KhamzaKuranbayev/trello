package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListGroupCriteria extends GenericCriteria {
    private String name;
    private Long taskId;

    @Builder(builderMethodName = "childBuilder")
    public CheckListGroupCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, Long taskId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.taskId = taskId;
    }
}
