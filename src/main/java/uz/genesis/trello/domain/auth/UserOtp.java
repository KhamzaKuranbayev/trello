package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;

/**
 * Created by 'ergashev_sh' on 30.07.2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_user_otps")
public class UserOtp extends Auditable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "otp_code", length = 6, unique = true)
    private String otpCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "otp_type", referencedColumnName = "id")
    private Type otpType;
}
