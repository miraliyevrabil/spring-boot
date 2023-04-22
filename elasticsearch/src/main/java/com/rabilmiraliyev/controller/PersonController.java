package com.rabilmiraliyev.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import com.rabilmiraliyev.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rabilmiraliyev.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @PostConstruct
    public void createTest()
    {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setId("1");
        person1.setName("Rabil");
        person1.setSurname("Miraliyev");
        personList.add(person1);

        Person person2 = new Person();
        person2.setId("2");
        person2.setName("Miraliyev");
        person2.setSurname("Rabil");
        personList.add(person2);

        Person person3 = new Person();
        person3.setId("3");
        person3.setName("Miraliyev");
        person3.setSurname("Rauf");
        personList.add(person3);

        Person person4 = new Person();
        person4.setId("4");
        person4.setName("Rauf");
        person4.setSurname("Miraliyev");
        personList.add(person4);

        personRepository.saveAll(personList);

    }

    @GetMapping("/findBy/{name}")
    public ResponseEntity<?> findById (@PathVariable String name){
        return ResponseEntity.ok(personRepository.findByCustomQuery(name));
    }

    @GetMapping("/findBy/{id}/{surname}")
    public ResponseEntity<?> findPersonByIdOrSurnameLike (@PathVariable String id,
                                       @PathVariable String surname){
        return ResponseEntity.ok(personRepository.findPersonByIdOrSurnameLike(id,surname));
    }

    @GetMapping("/findPersonBySurname/{surname}")
    public ResponseEntity<?> findPersonBySurname (@PathVariable String surname){
        return ResponseEntity.ok(personRepository.findPersonBySurname(surname));
    }

    @GetMapping("/findByMultipleFields/{query}")
    public ResponseEntity<?> findByMultipleFields (@PathVariable String query){
        return ResponseEntity.ok(personRepository.findByMultipleFields(query));
    }

    @GetMapping("/findByMultipleMatchFields/{query}")
    public ResponseEntity<?> findByMultipleMatchFields (@PathVariable String query){
        return ResponseEntity.ok(personRepository.findByMultipleMatchFields(query));
    }
}
