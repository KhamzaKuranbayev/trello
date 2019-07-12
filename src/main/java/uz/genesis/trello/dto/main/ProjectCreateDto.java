package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCreateDto extends GenericCrudDto {
    private String name;
    private GenericDto group;
    private GenericDto projectType;

}
