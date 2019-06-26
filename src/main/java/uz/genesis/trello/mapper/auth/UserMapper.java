package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.auth.UserCreateDto;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Mapper
public interface UserMapper {

    @Mapping(ignore = true, target = "roles")
    User fromCreateDto(UserCreateDto dto);
}
