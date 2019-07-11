package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCriteria extends GenericCriteria {

    private String name;
    private String codeName;

    @Builder(builderMethodName = "childBuilder")
    public PermissionCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, String codeName) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.codeName = codeName;
    }
}
