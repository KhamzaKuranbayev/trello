package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskTagService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskTagController extends ApiController<ITaskTagService> {
    public TaskTagController(ITaskTagService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskTags/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TaskTagDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskTags", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TaskTagCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/taskTags", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<TaskTagDto>> update(@RequestBody TaskTagUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/taskTags/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskTags", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<TaskTagDto>>> getAll(@Valid TaskTagCriteria criteria) {
        return service.getAll(criteria);
    }
}
