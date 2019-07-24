package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.dto.main.TaskActionDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ITaskActionService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskActionController extends ApiController<ITaskActionService> {
    public TaskActionController(ITaskActionService service) {
        super(service);
    }


    @RequestMapping(value = API_PATH + V_1 + "/taskActions", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<TaskActionDto>>> getAll(@Valid TaskActionCriteria criteria) {
        return service.getAll(criteria);
    }

}
