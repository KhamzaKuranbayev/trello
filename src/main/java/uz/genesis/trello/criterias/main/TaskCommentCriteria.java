package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCommentCriteria extends GenericCriteria {
    private Long taskId;
    private String commentText;
    private boolean count;

    @Builder(builderMethodName = "childBuilder")
    public TaskCommentCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long taskId, String commentText, boolean count) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.taskId = taskId;
        this.count = count;
        this.commentText = commentText;
    }
}
