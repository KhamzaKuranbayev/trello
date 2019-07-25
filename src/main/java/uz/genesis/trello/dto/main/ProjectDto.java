package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.dto.settings.TypeDto;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto extends GenericDto {
    private String name;
    private GroupDto group;
    private String background;
    private List<ProjectMemberDto> members;
    private TypeDto projectType;

    @Builder(builderMethodName = "childBuilder")

    public ProjectDto(Long id, String name, GroupDto group, List<ProjectMemberDto> members, TypeDto projectType) {
        super(id);
        this.name = name;
        this.group = group;
        this.members = members;
        this.projectType = projectType;
    }
}
