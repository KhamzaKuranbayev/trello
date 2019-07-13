package uz.genesis.trello.controller.settings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.SubTypeCreateDto;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.service.settings.ITypeService;


@RestController
@Api(value = "Types")
public class TypeController extends ApiController<ITypeService> {
    public TypeController(ITypeService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/types/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<TypeDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/types", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody TypeCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/types", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<TypeDto>> update(@RequestBody TypeUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/types/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }

    @ApiOperation(value = "Creating sub types")
    @RequestMapping(value = API_PATH + V_1 + "/types/subTypes", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> createSubType(@RequestBody SubTypeCreateDto dto){
        return service.createSubType(dto);
    }
}
