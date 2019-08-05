package uz.genesis.trello.service.auth;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.dto.auth.AuthUserDto;
import uz.genesis.trello.dto.auth.SessionDto;
import uz.genesis.trello.dto.auth.UserOtpConfirmDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IAbstractService;

import javax.servlet.http.HttpServletRequest;

public interface IAuthService extends IAbstractService {
    ResponseEntity<DataDto<SessionDto>> login(AuthUserDto user, HttpServletRequest request);

    ResponseEntity<DataDto<Boolean>>logout(HttpServletRequest request);

    ResponseEntity<DataDto<SessionDto>> refreshToken(AuthUserDto user, HttpServletRequest request);

    ResponseEntity<DataDto<Boolean>> signInOtp(String userName);

    ResponseEntity<DataDto<SessionDto>> otpConfirm(UserOtpConfirmDto dto);
}
