package uz.genesis.trello.dto.main;

import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

public class ProjectColumnDto extends GenericDto {
    private String name;
    private String codeName;
    private String projectId;
    private TypeDto columnType;
    private Integer ordering;
}
