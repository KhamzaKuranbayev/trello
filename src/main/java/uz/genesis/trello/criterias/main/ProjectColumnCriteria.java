package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnCriteria extends GenericCriteria {
    private String name;
    private String codeName;
    private Long projectId;

    @Builder(builderMethodName = "childBuilder")
    public ProjectColumnCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, String codeName, Long projectId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.codeName = codeName;
        this.projectId = projectId;
    }
}
