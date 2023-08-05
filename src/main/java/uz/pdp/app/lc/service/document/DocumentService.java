package uz.pdp.app.lc.service.document;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.app.lc.dto.UploadDocumentResponse;
import uz.pdp.app.lc.entity.Document;
import uz.pdp.app.lc.repository.DocumentRepository;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static uz.pdp.app.lc.mapper.DocumentMapper.DOCUMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final Path fileLocation;

    @Autowired
    private DocumentRepository documentRepository;


    public DocumentService() {
        String fileUploadDir = "C:\\JAVA\\Java_Spring_Projects\\LC\\src\\main\\resources\\uploads";
        this.fileLocation = Paths.get(fileUploadDir)
                .toAbsolutePath().normalize();
    }

    public UploadDocumentResponse saveFile(MultipartFile file) {
        String fullFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = fileLocation.resolve(fullFileName);
            Files.copy(file.getInputStream(), targetLocation);
        } catch (FileAlreadyExistsException e) {
            String[] fileNameAndType = fullFileName.split("\\.");
            fullFileName = fileNameAndType[0] + System.currentTimeMillis() + "." + fileNameAndType[1];
            Path targetLocation = fileLocation.resolve(fullFileName);
            try {
                Files.copy(file.getInputStream(), targetLocation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Document document = Document.builder()
                .originalName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileDownloadUri(fileLocation + "\\" + file.getOriginalFilename())
                .size(file.getSize())
                .build();
        documentRepository.save(document);
        return DOCUMENT_MAPPER.toDto(document);
    }


    public Path downloadFile(String fileName) {
        return fileLocation.resolve(fileName);
    }
}
