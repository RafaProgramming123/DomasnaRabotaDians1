package com.example.kulturnostoriskonasledstvo.Service;

import com.example.kulturnostoriskonasledstvo.Domain.DataJson;
import com.example.kulturnostoriskonasledstvo.Domain.PlaceDetail;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class JsonDbService {
    private final RestTemplate restTemplate;
    private final String jsonDbUrl = "http://localhost:9090/data"; // URL of your jsonDB service

    public JsonDbService() {
        this.restTemplate = new RestTemplate();
    }

    public List<DataJson> getAllData() {
        ResponseEntity<List<DataJson>> response = restTemplate.exchange(
                jsonDbUrl + "/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DataJson>>() {}
        );
        return response.getBody();
    }

    public Set<String> getDistinctTourismAndHistoricTypes() {
        List<DataJson> allData = getAllData();

        Set<String> distinctTypes = allData.stream()
                .flatMap(data -> Stream.of(data.getTags().getTourism(), data.getTags().getHistoric()))
                .filter(Objects::nonNull) // Filter out null values
                .collect(Collectors.toSet());

        return distinctTypes;
    }
    public Set<String> getDistinctLocations() {
        List<DataJson> allData = getAllData();

        Set<String> distinctLocations = allData.stream()
                .map(DataJson::getLocation)
                .filter(Objects::nonNull) // Filter out null values
                .collect(Collectors.toSet());

        return distinctLocations;
    }
    public Set<String> getDistinctArea() {
        List<DataJson> allData = getAllData();

        Set<String> distinctarea = allData.stream()
                .map(DataJson::getArea)
                .filter(Objects::nonNull) // Filter out null values
                .collect(Collectors.toSet());

        return distinctarea;
    }
    public List<PlaceDetail> getAllPlaceDetails() {
        List<DataJson> allData = getAllData();

        return allData.stream()
                .filter(data -> data.getLat() != null && data.getLon() != null && data.getLat() != 0.0 && data.getLon() != 0.0)
                .map(data -> {
                    PlaceDetail detail = new PlaceDetail();
                    if (data.getTags() != null) {
                        detail.setName(data.getTags().getName());
                        detail.setDescription(data.getTags().getDescription());
                    }
                    detail.setLat(data.getLat());
                    detail.setLon(data.getLon());
                    detail.setArea(data.getArea());
                    return detail;
                })
                .collect(Collectors.toList());
    }
//    public List<PlaceDetail> getPlaceDetailsByArea(String area) {
//        List<DataJson> allData = getAllData();
//
//        return allData.stream()
//                .filter(data -> area.equals(data.getArea()))
//                .map(data -> {
//                    PlaceDetail detail = new PlaceDetail();
//                    if (data.getTags() != null) {
//                        detail.setName(data.getTags().getName());
//                        detail.setDescription(data.getTags().getDescription());
//                    }
//                    detail.setLat(data.getLat());
//                    detail.setLon(data.getLon());
//                    detail.setArea(data.getArea());
//                    return detail;
//                })
//                .collect(Collectors.toList());
//    }
public List<PlaceDetail> getFilteredPlaceDetails(String area, String address, String type) {
    List<DataJson> allData = getAllData();

    return allData.stream()
            .filter(data -> (area == null || (data.getArea() != null && data.getArea().equalsIgnoreCase(area)))
                    && (address == null || (data.getTags().getName() != null && data.getTags().getName().equalsIgnoreCase(address)))
                    && (type == null || (data.getTags() != null && (type.equalsIgnoreCase(data.getTags().getTourism()) || type.equalsIgnoreCase(data.getTags().getHistoric())))))
            .map(data -> {
                PlaceDetail detail = new PlaceDetail();
                if(data.getTags() != null) {
                    detail.setName(data.getTags().getName());
                    detail.setDescription(data.getTags().getDescription());
                    if (type != null && type.equalsIgnoreCase(data.getTags().getTourism())) {
                        detail.setType(data.getTags().getTourism());
                    } else if (type != null && type.equalsIgnoreCase(data.getTags().getHistoric())) {
                        detail.setType(data.getTags().getHistoric());
                    }
                }
                detail.setLat(data.getLat());
                detail.setLon(data.getLon());
                detail.setArea(data.getArea());
                detail.setType(data.getTags().getTourism());
                return detail;
            })
            .collect(Collectors.toList());
}

    public PlaceDetail getPlaceById(Long id) {
        // Retrieve the specific place based on the ID
        // This is a mock-up; implement the actual logic to retrieve the place.
        List<DataJson> allData = getAllData();
        return allData.stream()
                .filter(data -> data.getId().equals(id))
                .findFirst()
                .map(data -> {
                    PlaceDetail detail = new PlaceDetail();
                    if (data.getTags() != null) {
                        detail.setName(data.getTags().getName());
                        detail.setDescription(data.getTags().getDescription());
                    }
                    detail.setLat(data.getLat());
                    detail.setLon(data.getLon());
                    detail.setType(data.getType());
                    return detail;
                }).orElse(null);
    }




}



