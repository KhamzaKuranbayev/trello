package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.enums.UserType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by 'javokhir' on 28/06/2019
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hr_employee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    @Column(name = "birth_date")
    protected Date birthDate;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "middle_name")
    protected String middleName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
    @Column(name = "branch_id")
    private Long branchId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<EmployeeGroup> employeeGroups = new ArrayList<>();

    @Builder(builderMethodName = "childBuilder")
    public Employee(String email, String userName, String password, UserType state, Collection<Role> roles, Long userId, Date birthDate, String firstName, String middleName, String lastName, Long branchId, Long organizationId) {
        super(email, userName, password, null, state, organizationId, null, roles, null, null);
        this.userId = userId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.branchId = branchId;
    }
}
