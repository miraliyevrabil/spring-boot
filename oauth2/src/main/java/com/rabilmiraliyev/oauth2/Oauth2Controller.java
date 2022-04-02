package com.rabilmiraliyev.oauth2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/auth")
    public String auht(){
        return "You logged in with Google";
    }
}
