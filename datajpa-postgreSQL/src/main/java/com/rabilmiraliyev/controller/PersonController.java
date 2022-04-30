package com.rabilmiraliyev.controller;

import com.rabilmiraliyev.dto.PersonDto;
import com.rabilmiraliyev.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> add(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.save(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> listAll(){
        return ResponseEntity.ok(personService.getAll());
    }
}
