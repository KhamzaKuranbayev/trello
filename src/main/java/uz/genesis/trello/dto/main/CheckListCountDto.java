package uz.genesis.trello.dto.main;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckListCountDto {
    private Long taskId;
    private Long totalCount;
    private Long checkedCount;
}
