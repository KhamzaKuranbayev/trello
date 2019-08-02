package uz.genesis.trello.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = SIGNIN_OTP_URL,  method = RequestMethod.POST)
    public ResponseEntity<DataDto<Boolean>> signIn(@RequestParam(value = "param") String param) {
        return service.signInOtp(param);
    }
    @RequestMapping(value = OTP_CONFIRM_URL, method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> otpConfirm(@RequestParam (value = "key") String key) {
        return service.otpConfirm(key);
    }



    @RequestMapping(value = LOGOUT_URL, method = RequestMethod.GET)
    public ResponseEntity<DataDto<Boolean>> logout(HttpServletRequest request) {
        return service.logout(request);
    }
}
