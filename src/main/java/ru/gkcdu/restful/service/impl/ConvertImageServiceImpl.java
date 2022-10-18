package ru.gkcdu.restful.service.impl;

import ru.gkcdu.restful.service.ConvertService;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@Service
public class ConvertImageServiceImpl implements ConvertService{
    private String ORIG = "C:\\dev\\proj\\micro\\workFiles\\SRC\\doc20190124112501_024.tif";
    private String OUTPUT_FOLDER = "C:\\dev\\proj\\micro\\workFiles\\DEST\\";

    @Override
    public void convertFile() {
        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(ORIG);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        PdfDocument pdfDocument = null;
        try {
            pdfDocument = new PdfDocument(new PdfWriter(OUTPUT_FOLDER + "Безымянный.pdf"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Document document = new Document(pdfDocument);

        Image image = new Image(imageData);
        image.setWidth(pdfDocument.getDefaultPageSize().getWidth() - 50);
        image.setAutoScaleHeight(true);

        document.add(image);
        pdfDocument.close();
    }
}
