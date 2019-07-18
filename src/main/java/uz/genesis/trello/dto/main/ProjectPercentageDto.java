package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.mapper.hr.GroupMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPercentageDto extends GenericDto {
    private String name;
    private GroupDto group;
    private int percent;

    public ProjectPercentageDto(Project domain, int percent) {
        super(domain.getId());
        this.name = domain.getName();
        this.group = Mappers.getMapper(GroupMapper.class).toDto(domain.getGroup());
        this.percent = percent;
    }
    public ProjectPercentageDto(Project domain) {
        super(domain.getId());
        this.name = domain.getName();
        this.group = Mappers.getMapper(GroupMapper.class).toDto(domain.getGroup());
    }
}
