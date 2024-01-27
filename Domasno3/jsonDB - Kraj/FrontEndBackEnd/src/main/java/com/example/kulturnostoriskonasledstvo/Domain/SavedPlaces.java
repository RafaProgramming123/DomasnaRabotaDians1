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
    private Long id; // Unique ID for each saved place

    @ManyToOne
    private Users user; // Many SavedPlaces can belong to one user

    private String placeId; // Unique identifier for the place

    private String name;
    private String description;
    private String type;

    public SavedPlaces() {

    }
}
