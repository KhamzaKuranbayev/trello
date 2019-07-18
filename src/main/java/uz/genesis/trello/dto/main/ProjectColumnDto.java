package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnDto extends GenericDto {
    private String name;
    private String codeName;
    private Long projectId;
    private TypeDto columnType;
    private Integer ordering;
}
