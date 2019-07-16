package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskMemberCreateDto;
import uz.genesis.trello.dto.main.TaskMemberDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskMemberService;

@RestController
public class TaskMemberController extends ApiController<ITaskMemberService> {
    public TaskMemberController(ITaskMemberService service) {
        super(service);
    }
    @RequestMapping(value = API_PATH + V_1 + "/taskMembers/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TaskMemberDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskMembers", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TaskMemberCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/taskMembers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
