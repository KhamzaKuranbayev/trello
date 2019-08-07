package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;

/**
 * Created by 'ergashev_sh' on 07.08.2019
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_otps")
public class AuthOtp extends Auditable {

    @Column(name = "username", unique = true)
    protected String userName;

    @Column(name = "otp_code", length = 6)
    private String otpCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "otp_type", referencedColumnName = "id")
    private Type otpType;
}
