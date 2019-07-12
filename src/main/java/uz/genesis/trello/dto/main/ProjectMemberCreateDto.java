package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberCreateDto extends GenericCrudDto {
    private long projectId;
    private GenericDto employee;
}
