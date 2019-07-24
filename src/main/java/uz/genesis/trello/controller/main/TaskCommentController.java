package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskCommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskCommentController extends ApiController<ITaskCommentService> {
    public TaskCommentController(ITaskCommentService service) {
        super(service);
    }
    @RequestMapping(value = API_PATH + V_1 + "/taskComments/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TaskCommentDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskComments", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TaskCommentCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/taskComments", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<TaskCommentDto>> update(@RequestBody TaskCommentUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/taskComments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskComments", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<TaskCommentDto>>> getAll(@Valid TaskCommentCriteria criteria) {
        return service.getAll(criteria);
    }
}
