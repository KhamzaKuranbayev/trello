package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.achievement.UserExpenseCoin;
import uz.genesis.trello.domain.achievement.UserIncomeCoin;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.domain.settings.Language;
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

    public Employee(String email, String userName, String password, UserType state, Collection<Role> roles, String phoneNumber, Long organizationId, Language language, List<UserIncomeCoin> incomeCoins, List<UserExpenseCoin> expenseCoins, Date birthDate, String firstName, String middleName, String lastName) {
        super(email, userName, password, state, roles, phoneNumber, organizationId, language, incomeCoins, expenseCoins);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}
