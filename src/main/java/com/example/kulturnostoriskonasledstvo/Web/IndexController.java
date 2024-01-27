package com.example.kulturnostoriskonasledstvo.Web;


import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class IndexController {
    @GetMapping
    public String editProductPage( Model model) {

        return "index.html";
    }



}
