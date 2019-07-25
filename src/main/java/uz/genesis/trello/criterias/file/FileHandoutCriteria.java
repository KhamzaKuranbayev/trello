package uz.genesis.trello.criterias.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.criterias.GenericCriteria;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileHandoutCriteria extends GenericCriteria {
    private Long sourceId;
}
