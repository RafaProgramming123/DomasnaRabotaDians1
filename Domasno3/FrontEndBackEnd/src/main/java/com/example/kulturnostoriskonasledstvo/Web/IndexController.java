package com.example.kulturnostoriskonasledstvo.Web;


import com.example.kulturnostoriskonasledstvo.Service.JsonDbService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class IndexController {

    private final JsonDbService jsonDbService;

    public IndexController(JsonDbService jsonDbService) {
        this.jsonDbService = jsonDbService;
    }

    @GetMapping
    public String editProductPage(Model model) {
        model.addAttribute("dataList", jsonDbService.getAllData());
        model.addAttribute("type",jsonDbService.getDistinctTourismAndHistoricTypes());
        model.addAttribute("locations",jsonDbService.getDistinctArea());
        return "index.html";
    }

    @PostMapping("/search")
    public ModelAndView handleSearch(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String type
    ) {

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
        return modelAndView;
    }




}
