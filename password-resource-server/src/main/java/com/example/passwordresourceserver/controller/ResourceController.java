package com.example.passwordresourceserver.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @GetMapping(value = "/access",produces = MediaType.APPLICATION_JSON_VALUE)
    public String access(){
        return JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication(),true);
    }
}
