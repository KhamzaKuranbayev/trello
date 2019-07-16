package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCreateDto;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.dto.main.TaskUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskService;

@RestController
public class TaskController extends ApiController<ITaskService> {
    public TaskController(ITaskService service) {
        super(service);
    }
    @RequestMapping(value = API_PATH + V_1 + "/tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TaskDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/tasks", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TaskCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/tasks", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<TaskDto>> update(@RequestBody TaskUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
