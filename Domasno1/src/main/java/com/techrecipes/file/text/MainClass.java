package com.techrecipes.file.text;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techrecipes.file.text.Filters.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Specify the name of your JSON file in the resources directory
        String jsonFileName = "PodatociHistoricMuseum.json";

        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file from resources
            InputStream inputStream = MainClass.class.getClassLoader().getResourceAsStream(jsonFileName);

            if (inputStream != null) {
                // Read JSON content as a JsonNode
                JsonNode jsonNode = objectMapper.readTree(inputStream);

                // Iterate through the elements and convert them to Museum objects
                Iterator<JsonNode> elements = jsonNode.path("elements").elements();
                List<TemplateJson> data = new ArrayList<>();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    TemplateJson template = objectMapper.treeToValue(element, TemplateJson.class);
                    data.add(template);
                }

                // Create a Pipe with the WebsiteFilter
                Pipe pipe = new Pipe(List.of(new HasTags(),new IsNotAttraction(),new IsNotViewPoint(),new IsNotHotel(),new LastFilter()));
                // Process the museums through the filters
                List<TemplateJson> filteredNoData = pipe.process(data);

                DataWriter.writeDataToFile(filteredNoData, "filtered_data.json");

                // Print the filtered JSON content
                String filteredJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredNoData);
                System.out.println(filteredJsonString);
            } else {
                System.err.println("File not found: " + jsonFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}