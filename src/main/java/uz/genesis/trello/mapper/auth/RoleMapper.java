package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper  extends BaseMapper<Role, RoleDto, RoleCreateDto, RoleUpdateDto> {

}
