package uz.genesis.trello.criterias.settings;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeCriteria extends GenericCriteria {

    private String name;
    private String typeCode;
    private String value;

    @Builder(builderMethodName = "childBuilder")
    public TypeCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, String name, String typeCode, String value) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.name = name;
        this.typeCode = typeCode;
        this.value = value;
    }
}
