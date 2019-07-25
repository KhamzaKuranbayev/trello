package uz.genesis.trello.service.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.file.FileHandoutCreateDto;
import uz.genesis.trello.dto.file.ResourceFileCreateDto;
import uz.genesis.trello.dto.file.ResourceFileDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.exception.GenericRuntimeException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.property.FileStorageProperties;
import uz.genesis.trello.utils.BaseUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;

@Service
public class FileStorageService implements IFileStorageService {
    private final BaseUtils baseUtils;
    private final IResourceFileService resourceFileService;
    private final Path rootLocation;
    private final IFileHandoutService handoutService;

    @Autowired
    public FileStorageService(BaseUtils baseUtils, IResourceFileService resourceFileService, FileStorageProperties properties, IFileHandoutService handoutService) {
        this.baseUtils = baseUtils;
        this.resourceFileService = resourceFileService;
        this.rootLocation = Paths.get(properties.getUploadDir());
        this.handoutService = handoutService;
    }

    @PostConstruct
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not initialize dir", e);
        }
    }

    @Override
    public ResponseEntity<DataDto<ResourceFileDto>> storeFile(MultipartFile file, String json) throws IOException {
        ObjectNode object = (ObjectNode) new ObjectMapper().readTree(json);
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (file.isEmpty()) {
            throw new RequestObjectNullPointerException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            throw new RequestObjectNullPointerException(
                    "Cannot store file with relative path outside current directory "
                            + filename);
        }

        FileHandoutCreateDto handout = new FileHandoutCreateDto();
        handout.setSourceId(object.get("sourceId").asLong());
        GenericDto sourceType = new GenericDto();
        GenericDto sourceTypeParent = new GenericDto();
        sourceType.setId(object.get("sourceType").asLong());
        sourceTypeParent.setId(object.get("sourceTypeParent").asLong());
        handout.setSourceType(sourceType);
        handout.setSourceTypeParent(sourceTypeParent);
        Long domainId = handoutService.createFileHandout(handout);


        try {
            String fileNamePrefix = Objects.requireNonNull(StringUtils.split(filename, "."))[0];
            String fileExtention = StringUtils.getFilenameExtension(filename);
            String newFileName = baseUtils.encodeToMd5(fileNamePrefix) + new Date().getTime() + "." + fileExtention;
            Files.copy(file.getInputStream(), this.rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

            ResourceFileCreateDto fileCreateDto = new ResourceFileCreateDto();
            fileCreateDto.setObjectId(domainId);
            fileCreateDto.setName(newFileName);
            fileCreateDto.setUrl("/api/v1/resource/uploads/" + newFileName);
            fileCreateDto.setMimeType(file.getContentType());
            fileCreateDto.setSize(file.getSize());
            fileCreateDto.setDefault(object.get("isDefault").asBoolean());
            fileCreateDto.setObjectType(new TypeDto(object.get("objectType").get("value").asText()));

            ResourceFileDto fileDto = resourceFileService.createFile(fileCreateDto);
            return new ResponseEntity<>(new DataDto<>(fileDto), HttpStatus.CREATED);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }

    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new GenericRuntimeException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new GenericRuntimeException("File not found " + fileName, ex);
        }
    }


}
