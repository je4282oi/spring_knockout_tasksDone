package com.clara.taskdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/")
    //Displays webpage to 127.0.0.1:8080
    //Only one web page route in this app
    public String homePage () {
        return "index.html";
    }
}

