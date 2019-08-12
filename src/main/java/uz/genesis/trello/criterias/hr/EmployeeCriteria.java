package uz.genesis.trello.criterias.hr;

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
    private Long groupId;
    private Long projectId;
    private Boolean withPhoto;
    private Boolean projectRelated;
    private String middleName;
    private String branchId;
    private String name;
    private Boolean isWatcher;
    private Boolean isManager;



    @Builder(builderMethodName = "childBuilder")
    public EmployeeCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Date birthDate, String firstName, String lastName, String middleName, String branchId, String name, Long projectId, Boolean projectRelated, Long groupId, Boolean withPhoto) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.birthDate = birthDate;
        this.projectRelated = projectRelated;
        this.withPhoto = withPhoto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.branchId = branchId;
        this.name = name;
        this.projectId = projectId;
        this.groupId = groupId;
    }


}
