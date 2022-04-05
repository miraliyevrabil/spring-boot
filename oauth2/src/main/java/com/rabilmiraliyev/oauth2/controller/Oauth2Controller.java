package com.rabilmiraliyev.oauth2.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Oauth2Controller {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/list")
    public String auht(){
        return "You logged in with Google";
    }
}
