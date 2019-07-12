package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface PermissionMapper extends BaseMapper<Permission, PermissionDto, PermissionCreateDto, PermissionUpdateDto> {

}
