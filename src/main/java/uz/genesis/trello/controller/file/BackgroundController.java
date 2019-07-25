package uz.genesis.trello.controller.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.file.BackgroundCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.file.BackgroundCreateDto;
import uz.genesis.trello.dto.file.BackgroundDto;
import uz.genesis.trello.dto.file.BackgroundUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.file.IBackgroundService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BackgroundController extends ApiController<IBackgroundService> {
    public BackgroundController(IBackgroundService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/backgrounds/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<BackgroundDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/backgrounds", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody BackgroundCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/backgrounds", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<BackgroundDto>> update(@RequestBody BackgroundUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/backgrounds/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/backgrounds", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<BackgroundDto>>> getAll(@Valid BackgroundCriteria criteria) {
        return service.getAll(criteria);
    }
}
