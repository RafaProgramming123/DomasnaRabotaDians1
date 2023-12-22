package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LogInController {

    private final UsersService usersService;

    public LogInController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "LogIn";
    }

    @PostMapping("/loginPost")
    public String login(@RequestParam String username, @RequestParam String password) {

        System.out.println(username+" " + password);
        if (usersService.authenticateUser(username, password)) {
            return "redirect:/home";
        } else {

            return "redirect:/login";

        }
    }
}
