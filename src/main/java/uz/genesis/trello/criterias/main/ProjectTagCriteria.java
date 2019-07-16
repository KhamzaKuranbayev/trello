package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProjectTagCriteria extends GenericCriteria {
    private String name;
    private String color;
    private Long projectId;

    @Builder(builderMethodName = "childBuilder")
    public ProjectTagCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, String color, Long projectId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.color = color;
        this.projectId = projectId;
    }
}
