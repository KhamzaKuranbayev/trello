package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Collection;

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

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "birth_date")
    protected Date birthDate;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "branch_id")
    private Long branchId;

    @Builder(builderMethodName = "childBuilder")
    public Employee(String email, String userName, String password, Collection<Role> roles, Long userId, Date birthDate, String firstName, String middleName, String lastName, Long branchId) {
        super(email, userName, password, roles);
        this.userId = userId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.branchId = branchId;
    }
}
