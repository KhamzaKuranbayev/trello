package uz.genesis.trello.service.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.dto.response.DataDto;

import java.io.IOException;

public interface IFileStorageService {
    void init() throws Exception;

    ResponseEntity<DataDto<ResourceFileDto>> storeFile(MultipartFile file, String json) throws IOException;
}
