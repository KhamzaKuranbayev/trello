package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskMemberDto extends GenericDto {
    private Long taskId;
    private EmployeeDto employee;

}
