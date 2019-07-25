package uz.genesis.trello.criterias.file;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundCriteria extends GenericCriteria {
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public BackgroundCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
    }
}
