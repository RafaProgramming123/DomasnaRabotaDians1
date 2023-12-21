package com.example.jsondb.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class DataJson {
   private  String type;
   private Double lat;
   private Double lon;
   private String location;
   private String area;
    @Embedded
   private Tags tags;

   private String nodes;

   private String  members;
    @Id
    private Long id;
    public DataJson() {

    }
}
