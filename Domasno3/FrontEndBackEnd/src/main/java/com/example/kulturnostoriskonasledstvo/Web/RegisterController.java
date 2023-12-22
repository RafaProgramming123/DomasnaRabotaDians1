package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Domain.Users;
import com.example.kulturnostoriskonasledstvo.Service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UsersService usersService;

    public RegisterController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String showLoginPage() {
        return "register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        Users newUser = new Users(username, password, email); // Ideally, encrypt the password here
        usersService.saveUser(newUser);
        return "redirect:/login";
    }

}
