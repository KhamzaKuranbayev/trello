package uz.genesis.trello.controller.hr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.hr.GroupCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.hr.IGroupService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController extends ApiController<IGroupService> {
    public GroupController(IGroupService service) {
        super(service);
    }
    @RequestMapping(value = API_PATH + V_1 + "/groups/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<GroupDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/groups", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody GroupCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/groups", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<GroupDto>> update(@RequestBody GroupUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/groups/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
    @RequestMapping(value = API_PATH + V_1 + "/groups", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<GroupDto>>> getAll(@Valid GroupCriteria criteria) {
        return service.getAll(criteria);
    }
}
