package uz.genesis.trello.criterias.organization;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCriteria extends GenericCriteria {
    private String userName;
    private String email;

    @Builder(builderMethodName = "childBuilder")
    public OrganizationCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String userName, String email) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.userName = userName;
        this.email = email;
    }
}
