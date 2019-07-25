package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.CheckListGroupCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.CheckListGroupCreateDto;
import uz.genesis.trello.dto.main.CheckListGroupDto;
import uz.genesis.trello.dto.main.CheckListGroupUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.ICheckListGroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CheckListGroupController extends ApiController<ICheckListGroupService> {
    public CheckListGroupController(ICheckListGroupService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/checkListGroups/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<CheckListGroupDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/checkListGroups", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody CheckListGroupCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/checkListGroups", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<CheckListGroupDto>> update(@RequestBody CheckListGroupUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/checkListGroups/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/checkListGroups", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<CheckListGroupDto>>> getAll(@Valid CheckListGroupCriteria criteria) {
        return service.getAll(criteria);
    }
}
