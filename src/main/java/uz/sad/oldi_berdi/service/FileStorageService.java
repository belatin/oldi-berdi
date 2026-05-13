package uz.sad.oldi_berdi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.sad.oldi_berdi.exception.CustomException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String save(MultipartFile file){
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path resolve = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), resolve);
            return fileName;
        }catch (IOException e){
            throw new CustomException("File saqlashda xatolik");
        }
    }
}
