package uz.genesis.trello.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubTypeCreateDto extends GenericCrudDto {
    private String name;
    private String value;
    private Integer ordering;
    private String typeCode;
}
