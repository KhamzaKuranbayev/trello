package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hr_employee_groups")
public class EmployeeGroup extends Auditable {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "is_leader", columnDefinition = "boolean DEFAULT false")
    private boolean leader;
}
