package uz.genesis.trello.criterias.suras;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuraCriteria extends GenericCriteria {

    private Integer order;

    private String name;

    @Builder(builderMethodName = "childBuilder")
    public SuraCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Integer order, String name) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.order = order;
        this.name = name;
    }
}
