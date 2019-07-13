package uz.genesis.trello.mapper;

import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.dto.CrudDto;

import java.util.List;

/**
 *
 * @param <E> - Entity type parametr
 * @param <D> - Dto
 * @param <CD> - CrudDTO
 * @param <UD> - UpdateDTO
 */

public interface BaseMapper<E extends Auditable, D, CD extends CrudDto, UD extends CrudDto> {
    D toDto(E entity);
    E fromDto(D dto);
    List<D> toDto(List<E> entityList);
    List<E> fromDto(List<D> dtoList);
    E fromCreateDto(CD createDto);
    E fromUpdateDto(UD updateDto);
}
