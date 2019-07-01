package uz.genesis.trello.criterias.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCriteria extends GenericCriteria {
    private String firstName;
    private Date birthDate;
    private String lastName;
    private String middleName;
    private String branchId;
    private String name;



    @Builder(builderMethodName = "childBuilder")
    public EmployeeCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Date birthDate, String firstName, String lastName, String middleName, String branchId, String name) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.branchId = branchId;
        this.name = name;
    }


}
