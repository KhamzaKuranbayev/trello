package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListMemberDto extends GenericDto {
    private Long checkListId;
    private EmployeeDto employee;
}
