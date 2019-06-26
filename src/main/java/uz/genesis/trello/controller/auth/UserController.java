package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IUserService;

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

}
