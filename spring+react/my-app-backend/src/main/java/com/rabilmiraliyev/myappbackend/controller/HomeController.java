package com.rabilmiraliyev.myappbackend.controller;

import com.rabilmiraliyev.myappbackend.model.Books;
import com.rabilmiraliyev.myappbackend.model.Enums;
import com.rabilmiraliyev.myappbackend.repository.BooksRepository;
import com.rabilmiraliyev.myappbackend.repository.EnumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private EnumsRepository enumsRepository;

    @RequestMapping(value = "/books",method = RequestMethod.GET)
    public ResponseEntity<?> getBooks(){
        List<Books> enums = booksRepository.findAll();
        return ResponseEntity.ok(enums);
    }

}
