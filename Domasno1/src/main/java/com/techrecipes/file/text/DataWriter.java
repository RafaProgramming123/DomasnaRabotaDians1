package com.techrecipes.file.text;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    public static void writeDataToFile(List<TemplateJson> data, String outputFileName) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
