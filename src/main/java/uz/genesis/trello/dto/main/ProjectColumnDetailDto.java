package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectColumnDetailDto extends GenericDto {
    private String name;
    private String codeName;
    private Long projectId;
    private TypeDto columnType;
    private Integer ordering;
    private List<TaskProjectDetailDto> tasks;

}
