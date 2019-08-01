package uz.genesis.trello.controller.settings;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.ErrorMessageCreateDto;
import uz.genesis.trello.dto.settings.ErrorMessageDto;
import uz.genesis.trello.dto.settings.ErrorMessageUpdateDto;
import uz.genesis.trello.service.settings.IErrorMessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ErrorMessageController extends ApiController<IErrorMessageService> {
    public ErrorMessageController(IErrorMessageService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/errorMessages", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody ErrorMessageCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/errorMessages", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<ErrorMessageDto>> update(@RequestBody ErrorMessageUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/errorMessages/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/errorMessages/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ErrorMessageDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/errorMessages", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ErrorMessageDto>>> getAll(@Valid ErrorMessageCriteria criteria) {
        return service.getAll(criteria);
    }
}
