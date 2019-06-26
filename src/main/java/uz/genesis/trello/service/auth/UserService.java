package uz.genesis.trello.service.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.auth.PermissionDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.repository.auth.IUserRepository;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.utils.BaseUtils;

import java.util.stream.Collectors;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Service
public class UserService extends AbstractService<UserDto, UserCriteria, IUserRepository> implements IUserService {

    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    public UserService(IUserRepository repository, BaseUtils utils) {
        super(repository, utils);
    }

    @Override
    public ResponseEntity<DataDto<UserDto>> get(Long id) {
        User user = repository.find(UserCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(user)) {
            logger.error(String.format("user with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("user with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataDto<>(UserDto.childBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .roles(user.getRoles().stream().map(t -> RoleDto.childBuilder()
                        .roleName(t.getRoleName())
                        .permissions(t.getPermission().stream().map(p -> PermissionDto.childBuilder()
                                .name(p.getName()).build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()))
                .build()), HttpStatus.OK);
    }
}
