package com.rabilmiraliyev.swagger.controller;

import com.rabilmiraliyev.swagger.model.User;
import com.rabilmiraliyev.swagger.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  MessageSource messageSource;

    @PostConstruct
    public void init()
    {
        User user=new User();
        user.setName("rabil");
        user.setSurname("miraliyev");
        user.setId(1L);
        userRepository.save(user);
    }

    @PostMapping
    @ApiOperation(value = "save method")
    public ResponseEntity<?> save(@RequestBody @ApiParam(value = "istifadeci") User user)
    {
        try{
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "update method")
    public ResponseEntity<?> update(@RequestBody User user)
    {
        try{
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete method")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        User user =userRepository.getById(id);
        try{
            userRepository.delete(user);
            return ResponseEntity.ok("deleted");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find user by id")
    public ResponseEntity<?> findById(@PathVariable @ApiParam(value = "user id") Long id)
    {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping
    @ApiOperation(value = "find all users")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/say-hello")
    @ApiOperation(value = "check internationalizing")
    public ResponseEntity<?> sayHello(@RequestHeader(value = "Accept-Language",required = false) String locale){
        return ResponseEntity.ok(messageSource.getMessage("hello.message", null, Locale.forLanguageTag(locale)));
    }
}

