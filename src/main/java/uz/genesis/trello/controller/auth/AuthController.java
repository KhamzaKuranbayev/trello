package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.auth.AuthUserDto;
import uz.genesis.trello.dto.auth.SessionDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IAuthService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController extends ApiController<IAuthService> {

    public AuthController(IAuthService service) {
        super(service);
    }

    @RequestMapping(value = LOGIN_URL, method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody AuthUserDto dto, HttpServletRequest request) {
        return service.login(dto, request);
    }

    @RequestMapping(value = LOGOUT_URL, method = RequestMethod.GET)
    public ResponseEntity<DataDto<Boolean>> logout(HttpServletRequest request) {
        return service.logout(request);
    }
}
