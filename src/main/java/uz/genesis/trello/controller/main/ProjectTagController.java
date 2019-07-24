package uz.genesis.trello.controller.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.main.ProjectTagCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectTagCreateDto;
import uz.genesis.trello.dto.main.ProjectTagDto;
import uz.genesis.trello.dto.main.ProjectTagUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.main.IProjectTagService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectTagController extends ApiController<IProjectTagService> {
    public ProjectTagController(IProjectTagService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectTags/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<ProjectTagDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectTags", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody ProjectTagCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectTags", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<ProjectTagDto>> update(@RequestBody ProjectTagUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectTags/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/projectTags", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ProjectTagDto>>> getAll(@Valid ProjectTagCriteria criteria) {
        return service.getAll(criteria);
    }


}
