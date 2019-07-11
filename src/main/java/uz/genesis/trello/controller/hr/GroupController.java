package uz.genesis.trello.controller.hr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.hr.IGroupService;

public class GroupController extends ApiController<IGroupService> {
    public GroupController(IGroupService service) {
        super(service);
    }
    @GetMapping(value = API_PATH + V_1 + "/groups/{id}")
    public ResponseEntity<DataDto<GroupDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @PostMapping(value = API_PATH + V_1 + "/groups")
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody GroupCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/groups", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<GroupDto>> update(@RequestBody GroupUpdateDto dto){
        return service.update(dto);
    }
    @DeleteMapping(value = API_PATH + V_1 + "/groups/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
