package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberUpdateDto extends GenericCrudDto {
    private Long id;
    private long projectId;
    private GenericDto employee;
}
