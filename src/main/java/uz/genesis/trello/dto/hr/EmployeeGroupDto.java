package uz.genesis.trello.dto.hr;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeGroupDto extends GenericDto {
    private Long employeeId;
    private Long groupId;
    private boolean leader;

    @Builder(builderMethodName = "childBuilder")
    public EmployeeGroupDto(Long id, Long employeeId, Long groupId, boolean leader) {
        super(id);
        this.employeeId = employeeId;
        this.groupId = groupId;
        this.leader = leader;
    }
}
