package com.example.kulturnostoriskonasledstvo.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showLoginPage() {
        return "register";
    }

    @PostMapping("/register")
    public String login() {

        return "redirect:/login";
    }
}
