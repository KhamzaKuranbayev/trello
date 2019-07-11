package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleCriteria extends GenericCriteria {
    private String roleName;
    private String codeName;

    @Builder(builderMethodName = "childBuilder")
    public RoleCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String roleName, String codeName) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.roleName = roleName;
        this.codeName = codeName;
    }
}
