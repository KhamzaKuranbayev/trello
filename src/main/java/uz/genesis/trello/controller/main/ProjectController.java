package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.domain.main.ProjectDetailDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectDto;
import uz.genesis.trello.dto.main.ProjectPercentageDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.IProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController extends ApiController<IProjectService> {
    public ProjectController(IProjectService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ProjectDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects/details/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ProjectDetailDto>> getDetail(@PathVariable(value = "id") Long id) {
        return service.getProjectDetail(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody ProjectCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<ProjectDto>> update(@RequestBody ProjectUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projects", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ProjectDto>>> getAll(@Valid ProjectCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = API_PATH + V_1 + "projects/percentage", method = RequestMethod.POST)
    public ResponseEntity<DataDto<List<ProjectPercentageDto>>> getAllWithPercentage(@Valid ProjectCriteria criteria) {
        return service.getAllWithPercentage(criteria);
    }
}
