package uz.genesis.trello.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.genesis.trello.domain.auth.AuthTry;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.auth.AuthTryCreateDto;
import uz.genesis.trello.dto.auth.AuthTryDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthTryMapper extends BaseMapper<AuthTry, AuthTryDto, AuthTryCreateDto, GenericCrudDto> {
}
