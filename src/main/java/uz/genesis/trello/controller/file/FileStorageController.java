package uz.genesis.trello.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.file.IFileStorageService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = API_PATH + V_1 + "/resource/uploads/{fileName:.+}",method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName.replace("|", "/"));
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
