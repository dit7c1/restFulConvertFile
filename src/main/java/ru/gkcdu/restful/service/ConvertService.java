package ru.gkcdu.restful.service;

import java.io.InputStream;

public interface ConvertService {
    String convertImageFile(InputStream file);
    String convertDocFile(InputStream file);
}
