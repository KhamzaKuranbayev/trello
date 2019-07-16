package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectTagDto extends GenericDto {
    private String name;
    private String color;
    private long projectId;


}
