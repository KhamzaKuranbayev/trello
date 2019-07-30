package uz.genesis.trello.service.auth;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.AttachRoleDto;
import uz.genesis.trello.dto.auth.UserCreateDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.auth.UserUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public interface IUserService extends IGenericCrudService<UserDto, UserCreateDto, UserUpdateDto, Long, UserCriteria> {
    ResponseEntity<DataDto<UserDto>> attachRolesToUser(@NotNull AttachRoleDto dto);

    User findByUserName(String userName);

    List<Role> getRoles(String userName);
}
