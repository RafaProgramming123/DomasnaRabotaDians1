package com.example.jsondb;

import com.example.jsondb.domain.DataJson;
import com.example.jsondb.service.DataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JsonDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(DataService dataService) {
        return args -> {
            //read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<DataJson>> typeReference = new TypeReference<List<DataJson>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/projectJson/jsonP.json");
            try {
                List<DataJson> users = mapper.readValue(inputStream, typeReference);
                dataService.save(users);
                System.out.println("Uspesen obid za Vnesuvanje Podatoci vo Baza!");
            } catch (IOException e) {
                System.out.println("Neuspesno: " + e.getMessage());
            }
        };
    }

}
