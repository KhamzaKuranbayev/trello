package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.auth.UserLastLogin;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.auth.UserLastLoginCreateDto;
import uz.genesis.trello.dto.auth.UserLastLoginDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserLastLoginMapper extends BaseMapper<UserLastLogin, UserLastLoginDto, UserLastLoginCreateDto, GenericCrudDto> {
}
