package com.example.jsondb.controller;

//import ch.qos.logback.core.model.Model;
import com.example.jsondb.domain.UsersApp;
import com.example.jsondb.service.UserService;
import org.springframework.ui.Model;
import com.example.jsondb.domain.DataJson;
import com.example.jsondb.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    private DataService dataService;
    private UserService userService;


    public DataController(DataService dataService,UserService userService) {
        this.dataService = dataService;
        this.userService=userService;
    }
    @GetMapping("/list")
        public Iterable<DataJson> list()
        {
            return dataService.list();
        }

        @GetMapping("/users")
            public Iterable<UsersApp> listUsers()
            {
                 return userService.listusers();
            }





}
