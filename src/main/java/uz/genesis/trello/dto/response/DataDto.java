package uz.genesis.trello.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDto<T> implements Serializable {

    protected T data;

    protected AppErrorDto error;

    protected boolean success;

    public DataDto(boolean success) {
        this.success = success;
    }

    public DataDto(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDto(AppErrorDto error) {
        this.error = error;
        this.success = false;
    }
}
