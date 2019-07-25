package uz.genesis.trello.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.file.IFileStorageService;

import java.io.IOException;

import static uz.genesis.trello.controller.ApiController.API_PATH;
import static uz.genesis.trello.controller.ApiController.V_1;

@RestController
public class FileStorageController {
    @Autowired
    public FileStorageController(IFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private final IFileStorageService fileStorageService;

    @RequestMapping(value = API_PATH + V_1 + "/resource/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<DataDto<ResourceFileDto>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "json") String json) throws IOException {
        return fileStorageService.storeFile(file, json);
    }
}
