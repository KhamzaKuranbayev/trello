package uz.genesis.trello.dto;

import com.google.gson.Gson;
import lombok.*;

/**
 * Created by 'javokhir' on 12/06/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericDto implements Dto {

    private Long id;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
