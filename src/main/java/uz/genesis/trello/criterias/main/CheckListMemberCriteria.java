package uz.genesis.trello.criterias.main;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CheckListMemberCriteria extends GenericCriteria {
    private Long checkListId;

    @Builder(builderMethodName = "childBuilder")
    public CheckListMemberCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long checkListId) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.checkListId = checkListId;
    }
}
