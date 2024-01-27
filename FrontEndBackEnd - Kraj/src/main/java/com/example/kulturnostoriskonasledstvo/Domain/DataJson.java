package com.example.kulturnostoriskonasledstvo.Domain;

import lombok.Data;

@Data
public class DataJson {
    private String type;
    private Double lat;
    private Double lon;
    private String location;
    private String area;
    private Tags tags;
    private String nodes;
    private String members;
    private Long id;


}