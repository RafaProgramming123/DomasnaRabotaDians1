package com.techrecipes.file.text;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TemplateJson {
    private String type;
    private long id;
    private double lat;
    private double lon;
    private Map<String, String> tags;
    List<Object> nodes;
    List<Object> members;

    // Constructors, getters, and setters

    public TemplateJson() {
    }

    public TemplateJson(String type, long id, double lat, double lon, Map<String, String> tags, List<Object> nodes, List<Object> members) {
        this.type = type;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.tags = tags;

    }
}