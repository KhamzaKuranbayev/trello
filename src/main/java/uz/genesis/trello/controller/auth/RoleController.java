package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IRoleService;

@RestController
public class RoleController extends ApiController<IRoleService> {
    public RoleController(IRoleService service) {
        super(service);
    }
    @GetMapping(value = API_PATH + V_1 + "/roles/{id}")
    public ResponseEntity<DataDto<RoleDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @PostMapping(value = API_PATH + V_1 + "/roles")
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody RoleCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/roles", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<RoleDto>> update(@RequestBody RoleUpdateDto dto){
        return service.update(dto);
    }
    @DeleteMapping(value = API_PATH + V_1 + "/roles/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
