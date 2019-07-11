package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.auth.UserUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(ignore = true, target = "roles")
    User fromCreateDto(UserCreateDto dto);

    @Mapping(source = "roles", target = "roles")
    User fromDto(UserDto userDto);

    @Mapping(source = "roles", target = "roles")
    UserDto fromUserToDto(User user);
}
