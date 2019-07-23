package uz.genesis.trello.service.main;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.main.ProjectMemberCreateDto;
import uz.genesis.trello.dto.main.ProjectMemberDto;
import uz.genesis.trello.dto.main.ProjectMemberUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface IProjectMemberService extends IGenericCrudService<ProjectMemberDto, ProjectMemberCreateDto, ProjectMemberUpdateDto, Long, ProjectMemberCriteria> {
    List<ProjectMemberDto> getAllProjectMembers(ProjectMemberCriteria criteria);

    ResponseEntity<DataDto<List<EmployeeDto>>> getEmployeeListByProjectId(ProjectMemberCriteria criteria);
}
