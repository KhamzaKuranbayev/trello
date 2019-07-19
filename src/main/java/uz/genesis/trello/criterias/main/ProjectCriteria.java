package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCriteria extends GenericCriteria {
    private String name;
    private boolean percentage;

    @Builder(builderMethodName = "childBuilder")
    public ProjectCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, boolean percentage) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.percentage = percentage;
    }
}
