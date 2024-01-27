package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Service.JsonDbService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view-by-place")
public class ViewController {
    private final JsonDbService jsonDbService;

    public ViewController(JsonDbService jsonDbService) {
        this.jsonDbService = jsonDbService;
    }

    @GetMapping()
    public String viewByPlace( Model model) {
        model.addAttribute("dataList", jsonDbService.getAllData());
        return "dataView";
    }
}