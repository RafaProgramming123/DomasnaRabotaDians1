package com.example.jsondb.controller;

//import ch.qos.logback.core.model.Model;
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

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
    @GetMapping("/list")
        public Iterable<DataJson> list()
        {
            return dataService.list();
        }


}
