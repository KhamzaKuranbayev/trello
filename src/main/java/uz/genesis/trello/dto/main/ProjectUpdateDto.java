package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDto extends GenericCrudDto {
    private long id;
    private String name;
    private GenericDto group;
    private List<GenericDto>  members;
    private GenericDto projectType;
}
