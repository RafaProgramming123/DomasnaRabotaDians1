package com.example.kulturnostoriskonasledstvo.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "LogIn";
    }

    @PostMapping("/login")
    public String login() {

        return "redirect:/home";
    }
}
