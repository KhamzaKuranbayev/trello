package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.TaskCheckListCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCheckListCreateDto;
import uz.genesis.trello.dto.main.TaskCheckListDto;
import uz.genesis.trello.dto.main.TaskCheckListUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskCheckListService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskCheckListController extends ApiController<ITaskCheckListService> {
    public TaskCheckListController(ITaskCheckListService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskCheckLists/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TaskCheckListDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskCheckLists", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TaskCheckListCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskCheckLists", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<TaskCheckListDto>> update(@RequestBody TaskCheckListUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskCheckLists/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/taskCheckLists", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<TaskCheckListDto>>> getAll(@Valid TaskCheckListCriteria criteria) {
        return service.getAll(criteria);
    }
}
