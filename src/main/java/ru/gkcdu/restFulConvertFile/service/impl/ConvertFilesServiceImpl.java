package ru.gkcdu.restFulConvertFile.service.impl;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import ru.gkcdu.restFulConvertFile.service.ConvertService;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ConvertFilesServiceImpl implements ConvertService {
    private String OUTPUT_FOLDER = "workFiles/DEST/tempFile.pdf";

    @Override
    public String convertImageFile(InputStream inputStreamFile) {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(inputStreamFile.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PdfDocument pdfDocument = null;
        try {
            pdfDocument = new PdfDocument(new PdfWriter(OUTPUT_FOLDER));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Document document = new Document(pdfDocument);
        Image image = new Image(imageData);

        image.setWidth(pdfDocument.getDefaultPageSize().getWidth() - 50);
        image.setAutoScaleHeight(true);
        document.add(image);

        document.close();
        pdfDocument.close();

        try {
            inputStreamFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return OUTPUT_FOLDER;
    }

    @Override
    public String convertDocFile(InputStream inputStreamFile) {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IConverter converter = LocalConverter.builder().build();
        converter.convert(inputStreamFile).as(DocumentType.DOC).to(new File(OUTPUT_FOLDER)).as(DocumentType.PDF).execute();

        converter.shutDown();

        try {
            inputStreamFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return OUTPUT_FOLDER;
    }
}
