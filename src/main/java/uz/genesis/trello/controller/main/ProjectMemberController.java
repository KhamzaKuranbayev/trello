package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.main.ProjectMemberCreateDto;
import uz.genesis.trello.dto.main.ProjectMemberDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.IProjectMemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectMemberController extends ApiController<IProjectMemberService> {
    public ProjectMemberController(IProjectMemberService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectMembers/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ProjectMemberDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectMembers", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody ProjectMemberCreateDto dto) {
        return service.create(dto);
    }

    //    @RequestMapping(value = API_PATH+V_1+"/projectMembers", method = {RequestMethod.PUT, RequestMethod.PATCH})
//    public ResponseEntity<DataDto<ProjectMemberDto>> update(@RequestBody ProjectMemberUpdateDto dto){
//        return service.update(dto);
//    }
//    @RequestMapping(value = API_PATH + V_1 + "/projectMembers/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
//        return service.delete(id);
//    }
    @RequestMapping(value = API_PATH + V_1 + "/projectMembers", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ProjectMemberDto>>> getAll(@Valid ProjectMemberCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectMembers/employee", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<EmployeeDto>>> getEmployeeListByProjectId(@Valid ProjectMemberCriteria criteria) {
        return service.getEmployeeListByProjectId(criteria);
    }

}
