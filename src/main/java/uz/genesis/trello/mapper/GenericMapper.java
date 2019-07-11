package uz.genesis.trello.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.dto.GenericDto;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface GenericMapper {

    GenericDto fromDomain(Auditable domain);
}
