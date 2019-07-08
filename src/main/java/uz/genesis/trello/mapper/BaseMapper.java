package uz.genesis.trello.mapper;

import java.util.List;

/**
 *
 * @param <E> - Entity type parametr
 * @param <D> - Dto
 * @param <CD> - CrudDTO
 * @param <UD> - UpdateDTO
 */

public interface BaseMapper<E, D, CD, UD> {
    D toDto(E entity);
    E fromDto(E entity);
    List<D> toDto(List<E> entityList);
    List<E> fromDto(List<D> dtoList);
    E fromCreateDto(CD createDto);
    E fromUpdateDto(UD updateDto);
}
