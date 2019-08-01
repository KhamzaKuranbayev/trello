package uz.genesis.trello.controller.settings;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.LanguageDto;
import uz.genesis.trello.service.settings.ILanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LanguageController extends ApiController<ILanguageService> {

    public LanguageController(ILanguageService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/languages/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<LanguageDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/languages", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<LanguageDto>>> getAll(@Valid LanguageCriteria criteria) {
        return service.getAll(criteria);
    }


}
