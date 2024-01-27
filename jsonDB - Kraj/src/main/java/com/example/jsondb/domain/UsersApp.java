package com.example.jsondb.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class UsersApp {

    @Id
    private String Username;
    private String Password;
    private String Email;

    public UsersApp() {

    }
}
