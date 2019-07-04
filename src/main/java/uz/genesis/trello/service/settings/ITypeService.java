package uz.genesis.trello.service.settings;

import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

/**
 * Created by 'javokhir' on 01/07/2019
 */

public interface ITypeService extends IGenericCrudService<TypeDto, TypeCreateDto, TypeUpdateDto, Long, TypeCriteria> {

}
