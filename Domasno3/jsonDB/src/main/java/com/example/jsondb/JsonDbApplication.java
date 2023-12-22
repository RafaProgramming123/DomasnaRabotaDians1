package com.example.jsondb;

import com.example.jsondb.domain.DataJson;
import com.example.jsondb.domain.UsersApp;
import com.example.jsondb.service.DataService;
import com.example.jsondb.service.UserService;
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
    CommandLineRunner runner(DataService dataService,UserService userService) {
        return args -> {
            //read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<DataJson>> typeReference = new TypeReference<List<DataJson>>() {};
            TypeReference<List<UsersApp>> typeReference1=new TypeReference<List<UsersApp>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/projectJson/jsonP.json");
            InputStream inputStream1=TypeReference.class.getResourceAsStream("/projectJson/users.json");
            try {
                List<DataJson> users = mapper.readValue(inputStream, typeReference);
                dataService.save(users);
                List<UsersApp> usersApp=mapper.readValue(inputStream1,typeReference1);
               userService.save(usersApp);
                System.out.println("Uspesen obid za Vnesuvanje Podatoci vo Baza!");
            } catch (IOException e) {
                System.out.println("Neuspesno: " + e.getMessage());
            }
        };
    }

}
