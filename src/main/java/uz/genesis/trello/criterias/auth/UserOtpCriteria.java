package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpCriteria extends GenericCriteria {
    private Long userId;

    @Builder(builderMethodName = "childBuilder")
    public UserOtpCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long userId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.userId = userId;
    }
}
