package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends GenericDto {
  private Long projectId;
  private Long columnId;
  private String name;
  private String description;
  private String deadLine;
  private Integer ordering;
  private String startAt;

}
