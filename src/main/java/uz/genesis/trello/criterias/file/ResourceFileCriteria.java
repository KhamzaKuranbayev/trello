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
public class ResourceFileCriteria extends GenericCriteria {
    private String name;
    private String mimeType;
    private Long size;
    private boolean isDefault;
}
