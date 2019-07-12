package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.service.IGenericCrudService;

public interface IProjectMemberService extends IGenericCrudService<ProjectMemberDto, ProjectMemberCreateDto, ProjectMemberUpdateDto, Long, ProjectMemberCriteria> {
}
