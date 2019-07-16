package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IRoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleController extends ApiController<IRoleService> {
    public RoleController(IRoleService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/roles/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<RoleDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/roles", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody RoleCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/roles", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<RoleDto>> update(@RequestBody RoleUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/roles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/roles", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<RoleDto>>> getAll(@Valid RoleCriteria criteria) {
        return service.getAll(criteria);
    }
}
