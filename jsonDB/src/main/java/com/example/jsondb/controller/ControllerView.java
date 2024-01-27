package com.example.jsondb.controller;

import com.example.jsondb.domain.DataJson;
import com.example.jsondb.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class ControllerView {


private final DataService dataService;

    public ControllerView(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/listHtml")
    public String list(Model model) {
        Iterable<DataJson> dataList = dataService.list();
        model.addAttribute("dataList", dataList);
        return "list1";
    }
}
