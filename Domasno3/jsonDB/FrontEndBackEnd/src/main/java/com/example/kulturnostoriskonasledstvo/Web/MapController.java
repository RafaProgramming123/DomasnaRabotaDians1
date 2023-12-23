package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Domain.PlaceDetail;
import com.example.kulturnostoriskonasledstvo.Service.JsonDbService;
import com.example.kulturnostoriskonasledstvo.Service.SavedPlacesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    private final JsonDbService jsonDbService;
    private final ObjectMapper objectMapper;
    private final SavedPlacesService savedPlacesService;


    public MapController(JsonDbService jsonDbService, ObjectMapper objectMapper, SavedPlacesService savedPlacesService) {
        this.jsonDbService = jsonDbService;
        this.objectMapper = objectMapper;

        this.savedPlacesService = savedPlacesService;
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
            Model model,
            HttpSession session
    ) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        try {
            List<PlaceDetail> filteredDetails = jsonDbService.getFilteredPlaceDetails(area, address, type);
            System.out.print(filteredDetails);
            String museumsJson = objectMapper.writeValueAsString(filteredDetails);
            model.addAttribute("museumsJson", museumsJson);
         //   List<String> locations= (List<String>) jsonDbService.getDistinctLocations();
            model.addAttribute("locations", jsonDbService.getDistinctArea());
            model.addAttribute("type",jsonDbService.getDistinctTourismAndHistoricTypes());
            System.out.println(type);
            model.addAttribute("selectedArea", area);
            model.addAttribute("selectedType",type);
            model.addAttribute("names",jsonDbService.getAllData());
        } catch (Exception e) {
            e.printStackTrace(); // handle the exception appropriately
        }
        return "map";
    }

    @PostMapping("/search")
    public ModelAndView handleSearch(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String type,
            HttpSession session
    ) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        System.out.println(currentUser);
        ModelAndView modelAndView = new ModelAndView("redirect:/map");
        if (area != null && !area.isEmpty()) {
            modelAndView.addObject("area", area);
        }
        if (address != null && !address.isEmpty()) {
            modelAndView.addObject("address", address);
        }
        if (type != null && !type.isEmpty()) {
            modelAndView.addObject("type", type);
        }

        System.out.print(area);
        return modelAndView;
    }

    @PostMapping("/addFavorite")
    public String addFavoritePlace(
            @RequestParam("placeId") Long placeId, // Accept Long directly
            HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        PlaceDetail place = jsonDbService.getPlaceById(placeId); // Retrieve the place details
        if (place != null) {
            savedPlacesService.savePlace(currentUser, placeId.toString(), place.getName(), place.getDescription(), place.getType());
        } else {
            // Handle the case where the place isn't found
            // Log error or show an error message
        }
        return "redirect:/map";
    }



}
