package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTryCriteria extends GenericCriteria {
    private String userName;
    private String ipAddress;

    @Builder(builderMethodName = "childBuilder")
    public AuthTryCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String userName, String ipAddress) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.userName = userName;
        this.ipAddress = ipAddress;
    }
}
