package uz.genesis.trello.domain.settings;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 'javokhir' on 08/07/2019
 */


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings_db_log_errors")
public class DbLogError extends Auditable {

    @Column(name = "v_state", length = 2048)
    private String state;

    @Column(name = "v_msg", length = 2048)
    private String message;

    @Column(name = "v_detail", length = 2048)
    private String detail;

    @Column(name = "v_hint", length = 2048)
    private String hint;

    @Column(name = "v_context", length = 2048)
    private String context;

    @Column(name = "v_function_name", length = 512)
    private String functionName;

    @Column(name = "params", length = 8000)
    private String params;
}
