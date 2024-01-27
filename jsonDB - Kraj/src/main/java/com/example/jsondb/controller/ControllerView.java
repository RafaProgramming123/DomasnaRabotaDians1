package com.example.jsondb.controller;

import com.example.jsondb.domain.DataJson;
import com.example.jsondb.domain.UsersApp;
import com.example.jsondb.service.DataService;
import com.example.jsondb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class ControllerView {


    private final DataService dataService;
    private final UserService userService;

    public ControllerView(DataService dataService, UserService userService) {
        this.dataService = dataService;
        this.userService = userService;
    }

    @GetMapping("/listHtml")
    public String list(Model model) {
        Iterable<DataJson> dataList = dataService.list();
        model.addAttribute("dataList", dataList);

        return "list1";
    }

    @GetMapping("/usersHtml")
    public String listusers(Model model) {
        Iterable<UsersApp> userList = userService.listusers();
        model.addAttribute("userList", userList);

        return "users";
    }
}