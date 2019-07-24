package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskService;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = API_PATH + V_1 + "/tasks", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<TaskDto>> update(@RequestBody TaskUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/tasks", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<TaskDto>>> getAll(@Valid TaskCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = API_PATH + V_1 + "/tasks/move", method = RequestMethod.POST)
    public ResponseEntity<DataDto<TaskDto>> move(MovingTaskDto dto) {
        return service.move(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/tasks/timeSheet", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> createTaskTimeEntry(TaskTimeEntryCreateDto dto) {
        return service.createTaskTimeEntry(dto);
    }

}
