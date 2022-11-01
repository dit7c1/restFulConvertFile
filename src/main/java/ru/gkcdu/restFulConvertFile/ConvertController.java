package ru.gkcdu.restFulConvertFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.gkcdu.restFulConvertFile.service.ConvertService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
public class ConvertController {
    private final ConvertService convertService;

    @Autowired
    public ConvertController(ConvertService convertService) {
        this.convertService = convertService;
    }

    /*@PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> convertImage(@RequestParam("file") MultipartFile file) {
        convertService.convertFile(file);
        //ResponseEntity<> test = new ResponseEntity<>().;
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> convertImage(@RequestParam("file") MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newFileName = Objects.requireNonNull(file.getOriginalFilename()).
                substring(0, file.getOriginalFilename().lastIndexOf(".")) + ".pdf";
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).
                    header("Content-Disposition", "filename=" + newFileName).
                    body(Files.readAllBytes(Paths.get(convertService.convertImageFile(is))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/doc", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> convertDoc(@RequestParam("file") MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String newFileName = Objects.requireNonNull(file.getOriginalFilename()).
                substring(0, file.getOriginalFilename().lastIndexOf(".")) + ".pdf";
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).
                    header("Content-Disposition", "filename=" + newFileName).
                    body(Files.readAllBytes(Paths.get(convertService.convertDocFile(is))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
