package uz.genesis.trello.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto extends GenericDto {

    private String name;
    private String typeCode;
    private String value;
    private Integer ordering;
}
