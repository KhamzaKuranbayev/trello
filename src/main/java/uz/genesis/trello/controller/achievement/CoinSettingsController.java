package uz.genesis.trello.controller.achievement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.achievement.CoinSettingsCriteria;
import uz.genesis.trello.dto.achievement.CoinSettingsCrudDto;
import uz.genesis.trello.dto.achievement.CoinSettingsDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.achievement.ICoinSettingsService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CoinSettingsController extends ApiController<ICoinSettingsService> {
    public CoinSettingsController(ICoinSettingsService service) {
        super(service);
    }

    @RequestMapping(value = API_PATH + V_1 + "/achievement/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<CoinSettingsDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/achievements", method = RequestMethod.POST)
    public ResponseEntity<DataDto<CoinSettingsDto>> saveAndUpdate(@RequestBody CoinSettingsCrudDto dto) {
        return service.saveAndUpdateCoinSettings(dto);
    }

    @RequestMapping(value = API_PATH + V_1 + "/achievements", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<CoinSettingsDto>>> getAll(@Valid CoinSettingsCriteria criteria) {
        return service.getAll(criteria);
    }
}
