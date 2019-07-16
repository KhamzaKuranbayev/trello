package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskMemberCriteria extends GenericCriteria {
    private Long taskId;

        @Builder(builderMethodName = "childBuilder")
        public TaskMemberCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long taskId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.taskId = taskId;
    }
}
