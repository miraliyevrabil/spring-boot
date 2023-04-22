package controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.PersonRepository;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @PostConstruct
    public void createTest()
    {
        for (int i=0;i<=10;i++) {
            Person person = new Person();
            person.setId(String.valueOf(i));
            person.setName("test name"+i);
            person.setSurname("test surname"+i);
            personRepository.save(person);
        }
    }

    

}
