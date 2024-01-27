package com.example.jsondb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Tags {
    private String name;
    @JsonProperty("name:en")
    private String nameEn;
    private String tourism;
    private String wikipedia;
    private String historic;
    private String artist;
    private  String description;
    private  String website;
    public Tags()
 {

 }

}
