package ru.gkcdu.restful.service.impl;
/*
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.springframework.stereotype.Service;
import ru.gkcdu.restful.service.ConvertService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ConvertDocServiceImpl implements ConvertService {
    private String OUTPUT_FOLDER = "workFiles/DEST/tempFile.pdf";

    @Override
    public String convertFile(InputStream file) {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream docxInputStream = null;
        try {
            docxInputStream = new BufferedInputStream(new FileInputStream(String.valueOf(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(OUTPUT_FOLDER);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        IConverter converter = LocalConverter.builder().build();
        converter.convert(docxInputStream).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        converter.shutDown();

        return OUTPUT_FOLDER;
    }
}
*/