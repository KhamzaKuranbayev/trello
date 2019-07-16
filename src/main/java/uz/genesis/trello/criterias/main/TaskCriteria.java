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
    private Boolean ownTask; // for example select t.* from main_project_tasks t inner join main_project_task_members m on t.id = m.task_id where m.employee_id = 11;

    @Builder(builderMethodName = "childBuilder")
    public TaskCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, Long projectId, Long columnId, String description, String deadLine, Boolean ownTask) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.projectId = projectId;
        this.columnId = columnId;
        this.description = description;
        this.deadLine = deadLine;
        this.ownTask = ownTask;
    }
}
