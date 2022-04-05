package com.rabilmiraliyev.oauth2.controller;


import com.rabilmiraliyev.oauth2.conf.CustomOAuth2User;
import com.rabilmiraliyev.oauth2.conf.CustomOAuth2UserService;
import com.rabilmiraliyev.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Oauth2Controller {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/auth")
    public String auht(){
        return "You logged in with Google";
    }

}
