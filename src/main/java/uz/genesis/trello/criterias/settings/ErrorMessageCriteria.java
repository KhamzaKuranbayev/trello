package uz.genesis.trello.criterias.settings;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageCriteria extends GenericCriteria {
    private String errorCode;


    @Builder(builderMethodName = "childBuilder")
    public ErrorMessageCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String errorCode) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.errorCode = errorCode;
    }
}
