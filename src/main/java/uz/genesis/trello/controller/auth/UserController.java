package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.AttachRoleDto;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.auth.UserUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IUserService;

import javax.validation.Valid;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@RestController
public class UserController extends ApiController<IUserService> {

    public UserController(IUserService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<UserDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/users", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody UserCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/users", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<UserDto>> update(@RequestBody UserUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/users/attachRole", method = RequestMethod.POST)
    public ResponseEntity<DataDto<UserDto>> attachRoles(@RequestBody AttachRoleDto dto){return service.attachRolesToUser(dto);}

}
