package com.example.kulturnostoriskonasledstvo.Domain;

import lombok.Data;

@Data
public class PlaceDetail {
    private String name;
    private String description;
    private Double lat;
    private Double lon;
    private String area;
    private String type;

}
