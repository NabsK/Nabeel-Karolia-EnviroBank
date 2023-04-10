package com.envirobank.fileparser;     

import java.io.File;
import java.net.URI;

public interface FileParser {       //blueprint for file parser

    void parseCSV(File csvFile);

    File convertCSVDataToImage(String base64ImageData);

    URI createImageLink(File fileImage);
}
