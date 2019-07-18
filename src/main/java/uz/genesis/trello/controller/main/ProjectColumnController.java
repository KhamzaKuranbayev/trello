package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.ProjectColumnCriteria;
import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.IProjectColumnService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectColumnController extends ApiController<IProjectColumnService>  {
    public ProjectColumnController(IProjectColumnService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectColumns/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ProjectColumnDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectColumns", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody ProjectColumnCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectColumns", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ProjectColumnDto>>> getAll(@Valid ProjectColumnCriteria criteria) {
        return service.getAll(criteria);
    }
    @RequestMapping(value = API_PATH+V_1+"/projectColumns", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<ProjectColumnDto>> update(@RequestBody ProjectColumnUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/projectColumns/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
