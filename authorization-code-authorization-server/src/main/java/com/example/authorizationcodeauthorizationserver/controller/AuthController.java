package com.example.authorizationcodeauthorizationserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping(value = "/access",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> access(@RequestParam("code") final String code){
        return new ResponseEntity<>("code=" + code, HttpStatus.OK);
    }
}
