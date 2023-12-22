package com.example.kulturnostoriskonasledstvo.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Users {
    @Id
    private String UserName;
    private String password;
    private String email;
    public Users() {
    }
}
