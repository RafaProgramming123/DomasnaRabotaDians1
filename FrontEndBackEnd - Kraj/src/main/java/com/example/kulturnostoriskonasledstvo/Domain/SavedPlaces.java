package com.example.kulturnostoriskonasledstvo.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class SavedPlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users user;

    private String placeId;

    private String name;
    private String description;
    private String type;

    public SavedPlaces() {

    }
}
