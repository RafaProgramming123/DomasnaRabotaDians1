package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Domain.DataJson;
import com.example.kulturnostoriskonasledstvo.Domain.PlaceDetail;
import com.example.kulturnostoriskonasledstvo.Domain.PlaceDetail;
import com.example.kulturnostoriskonasledstvo.Service.JsonDbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    private final JsonDbService jsonDbService;
    private final ObjectMapper objectMapper;

    public MapController(JsonDbService jsonDbService, ObjectMapper objectMapper) {
        this.jsonDbService = jsonDbService;
        this.objectMapper = objectMapper;
    }

//    @GetMapping
//    public String showMap(@RequestParam(required = false) String area, Model model) {
//        try {
//            List<PlaceDetail> filteredDetails = (area != null && !area.isEmpty())
//                    ? jsonDbService.getPlaceDetailsByArea(area)
//                    : jsonDbService.getAllPlaceDetails();
//
//            String museumsJson = objectMapper.writeValueAsString(filteredDetails);
//            model.addAttribute("museumsJson", museumsJson);
//            System.out.println(museumsJson);
//        } catch (Exception e) {
//            e.printStackTrace(); // handle the exception appropriately
//        }
//        return "map";
//    }
    @GetMapping
    public String showMap(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String type,
            Model model
    ) {
        try {
            List<PlaceDetail> filteredDetails = jsonDbService.getFilteredPlaceDetails(area, address, type);
            System.out.print(filteredDetails);
            String museumsJson = objectMapper.writeValueAsString(filteredDetails);
            model.addAttribute("museumsJson", museumsJson);
            System.out.println(type);

        } catch (Exception e) {
            e.printStackTrace(); // handle the exception appropriately
        }
        return "map";
    }


}
