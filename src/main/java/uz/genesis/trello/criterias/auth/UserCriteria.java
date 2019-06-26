package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

/**
 * Created by 'javokhir' on 11/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteria extends GenericCriteria {

    private String userName;

    @Builder(builderMethodName = "childBuilder")
    public UserCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String userName) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.userName = userName;
    }
}
