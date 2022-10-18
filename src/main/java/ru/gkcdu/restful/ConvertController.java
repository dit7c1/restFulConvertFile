package ru.gkcdu.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gkcdu.restful.service.ConvertService;

@RestController
public class ConvertController {
    private final ConvertService convertService;

    @Autowired
    public ConvertController(ConvertService convertService) {
        this.convertService = convertService;
    }

    @PostMapping(value = "/images")
    public ResponseEntity<?> convertImage() {
        convertService.convertFile();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
