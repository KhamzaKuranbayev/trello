package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberCriteria extends GenericCriteria {
    private Long projectId;
    @Builder(builderMethodName = "childBuilder")
    public ProjectMemberCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long projectId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.projectId = projectId;
    }
}
