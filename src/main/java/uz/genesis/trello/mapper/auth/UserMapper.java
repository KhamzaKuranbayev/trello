package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.auth.UserUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto, UserCreateDto, UserUpdateDto> {
    @Override
    @Mapping(ignore = true, target = "roles")
    User fromCreateDto(UserCreateDto dto);

    @Override
    @Mapping(source = "roles", target = "roles")
    UserDto toDto(User user);
}
