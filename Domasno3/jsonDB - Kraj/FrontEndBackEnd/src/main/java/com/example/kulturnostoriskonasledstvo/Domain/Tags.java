package com.example.kulturnostoriskonasledstvo.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Tags {
    private String name;
    private String nameEn;
    private String tourism;
    private String wikipedia;
    private String historic;
    private String artist;
    private  String description;
    private  String website;


}