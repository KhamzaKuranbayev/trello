package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TaskTag update request")
public class TaskTagUpdateDto extends GenericCrudDto {
    private Long id;
    private Long projectTagId;
    private Long taskId;

}
