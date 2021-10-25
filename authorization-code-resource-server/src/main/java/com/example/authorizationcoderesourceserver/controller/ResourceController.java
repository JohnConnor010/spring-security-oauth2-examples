package com.example.authorizationcoderesourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource-server")
public class ResourceController {
    @GetMapping(value = "/access")
    public String access(){
        return "resource-server:you are in";
    }
}
