package com.example.kulturnostoriskonasledstvo.Web;


import com.example.kulturnostoriskonasledstvo.Domain.SavedPlaces;
import com.example.kulturnostoriskonasledstvo.Service.SavedPlacesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fav")
public class FavouriteController {

    private final SavedPlacesService savedPlacesService;

    @Autowired
    public FavouriteController(SavedPlacesService savedPlacesService) {
        this.savedPlacesService = savedPlacesService;
    }
    @GetMapping
    public String editProductPage(Model model, HttpSession session) {

        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        List<SavedPlaces> savedPlaces = savedPlacesService.findPlacesByUser(currentUser);
        model.addAttribute("savedPlaces", savedPlaces);

        return "Favourite.html";
    }



}