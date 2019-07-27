package uz.genesis.trello.criterias.auth;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLastLoginCriteria extends GenericCriteria {
    private Long userId;
    private String sessionToken;
    private String logAt;
    private String ipAddress;

    @Builder(builderMethodName = "childBuilder")
    public UserLastLoginCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long userId, String sessionToken, String logAt, String ipAddress) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.logAt = logAt;
        this.ipAddress = ipAddress;
    }
}
