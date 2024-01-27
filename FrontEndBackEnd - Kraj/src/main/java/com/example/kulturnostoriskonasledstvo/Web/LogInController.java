package com.example.kulturnostoriskonasledstvo.Web;

import com.example.kulturnostoriskonasledstvo.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showLoginPage(@RequestParam(value = "message", required = false) String message, Model model) {
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        return "LogIn";
    }
    @PostMapping("/loginPost")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model,
                        @RequestParam(value = "message", required = false) String message) {

        System.out.println(username + " " + password);
        if (usersService.authenticateUser(username, password)) {
            session.setAttribute("currentUser", username);
            return "redirect:/home";
        } else {
            return "redirect:/login?message=Please+make+an+account+!";
        }

    }
}
