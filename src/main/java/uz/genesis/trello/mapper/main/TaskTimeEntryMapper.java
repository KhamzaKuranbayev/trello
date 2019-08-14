package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskTimeEntry;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskTimeEntryCreateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface TaskTimeEntryMapper extends BaseMapper<TaskTimeEntry, GenericDto, TaskTimeEntryCreateDto, CrudDto> {

}
