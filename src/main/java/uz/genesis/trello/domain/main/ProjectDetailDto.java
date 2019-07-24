package uz.genesis.trello.domain.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeGroupDto;
import uz.genesis.trello.dto.main.ProjectColumnDetailDto;
import uz.genesis.trello.dto.main.ProjectMemberDto;
import uz.genesis.trello.dto.main.ProjectTagDto;
import uz.genesis.trello.dto.main.TaskActionDto;
import uz.genesis.trello.dto.settings.TypeDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDetailDto extends GenericDto {
    private String name;
    private String codeName;
    private TypeDto projectType;
    private TypeDto projectLevelType;
    private TypeDto projectPriorityType;
    private List<EmployeeGroupDto> employeeGroups;
    private List<ProjectColumnDetailDto> columns;
    private EmployeeDto manager;
    private List<ProjectTagDto> tags;
    private List<ProjectMemberDto> members;
    private List<TaskActionDto> actions;

    @Builder(builderMethodName = "childBuilder")
    public ProjectDetailDto(Long id, String name, String codeName, TypeDto projectType, TypeDto projectLevelType, TypeDto projectPriorityType, List<EmployeeGroupDto> employeeGroups, List<ProjectColumnDetailDto> columns, EmployeeDto manager, List<ProjectTagDto> tags, List<ProjectMemberDto> members, List<TaskActionDto> actions) {
        super(id);
        this.name = name;
        this.codeName = codeName;
        this.projectType = projectType;
        this.projectLevelType = projectLevelType;
        this.projectPriorityType = projectPriorityType;
        this.employeeGroups = employeeGroups;
        this.columns = columns;
        this.manager = manager;
        this.tags = tags;
        this.members = members;
        this.actions = actions;
    }
}
