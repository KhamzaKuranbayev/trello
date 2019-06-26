package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.genesis.trello.controller.ApiController;
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
}
