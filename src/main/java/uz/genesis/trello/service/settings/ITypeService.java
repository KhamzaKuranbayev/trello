package uz.genesis.trello.service.settings;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.SubTypeCreateDto;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.enums.Types;
import uz.genesis.trello.service.IGenericCrudService;

/**
 * Created by 'javokhir' on 01/07/2019
 */

public interface ITypeService extends IGenericCrudService<TypeDto, TypeCreateDto, TypeUpdateDto, Long, TypeCriteria> {
 ResponseEntity<DataDto<GenericDto>> createSubType(SubTypeCreateDto dto);
 Long getIdByValue(Types type);

}
