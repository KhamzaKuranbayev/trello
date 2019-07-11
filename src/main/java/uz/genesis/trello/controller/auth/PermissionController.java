package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IPermissionService;

@RestController
public class PermissionController extends ApiController<IPermissionService> {

    public PermissionController(IPermissionService service) {
        super(service);
    }
    @RequestMapping(value = API_PATH + V_1 + "/permissions/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<PermissionDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/permissions", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody PermissionCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/permissions", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<PermissionDto>> update(@RequestBody PermissionUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/permissions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}