package uz.genesis.trello.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.UserOtpDto;
import uz.genesis.trello.repository.auth.IUserOtpRepository;
import uz.genesis.trello.repository.settings.TypeRepository;
import uz.genesis.trello.service.api.ApiConnector;
import uz.genesis.trello.utils.otp.OtpMessage;
import uz.genesis.trello.utils.otp.OtpUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static uz.genesis.trello.enums.Types.AUTH_OTP_TYPE_PHONE;

@Service
public class OtpHelperService implements IOtpHelperService {

    @Value("http://91.204.239.42:8083/broker-api/send")
    private String smsUrl;

    private TypeRepository typeRepository;
    private IUserOtpRepository userOtpRepository;
    private OtpUtils otpUtils;

    @Autowired
    public OtpHelperService(TypeRepository typeRepository, IUserOtpRepository userOtpRepository, OtpUtils otpUtils) {
        this.typeRepository = typeRepository;
        this.userOtpRepository = userOtpRepository;
        this.otpUtils = otpUtils;
    }

    @Override
    public void send(User user) {
        OtpMessage otpMessage = new OtpMessage(user.getPhoneNumber(), "5800", generateOpt(user));

        ApiConnector.newBuilder(String.class)
                .setUrl(smsUrl)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + otpUtils.generateMessage())
                .addParam("messages", new OtpMessage[]{otpMessage})
                .exchange(HttpMethod.POST)
                .build();//todo change it to simple http request see AuthService ask Bakhtiyor or Javokhir
    }

    public String generateOpt(User user) {
        List<FunctionParam> params = new ArrayList<>();
        params.add(new FunctionParam(AUTH_OTP_TYPE_PHONE, Types.BIGINT));
        Long typeId = userOtpRepository.call(params, "getidbytype", Types.BIGINT);

        return userOtpRepository.call(UserOtpDto.childBuilder()
                        .otpType(GenericDto.builder().id(typeId).build())
                        .userId(user.getId()).build(),
                "createUserOtp", Types.VARCHAR);
    }
}
