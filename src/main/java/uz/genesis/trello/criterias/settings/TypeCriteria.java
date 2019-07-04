package uz.genesis.trello.criterias.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
}
