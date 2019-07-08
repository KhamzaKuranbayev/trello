package uz.genesis.trello.dto.hr;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

/**
 * Created by 'javokhir' on 08/07/2019
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto extends GenericDto {

    private String name;

    private boolean watcher;

    @Builder(builderMethodName = "childBuilder")
    public GroupDto(Long id, String name, boolean watcher) {
        super(id);
        this.name = name;
        this.watcher = watcher;
    }
}
