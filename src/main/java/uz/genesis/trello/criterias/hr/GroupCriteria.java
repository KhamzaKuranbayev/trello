package uz.genesis.trello.criterias.hr;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCriteria  extends GenericCriteria {
    private String name;
    private boolean watcher;


    @Builder(builderMethodName = "childBuilder")
    public GroupCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, boolean watcher) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.watcher = watcher;
    }
}
