package com.example.implicitresourceserver.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping(value = "/resource/access",produces = MediaType.APPLICATION_JSON_VALUE)
    public String access(){
        return JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication());
    }
}