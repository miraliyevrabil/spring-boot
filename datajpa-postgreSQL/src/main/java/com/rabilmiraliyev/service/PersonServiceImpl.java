package com.rabilmiraliyev.service;

import com.rabilmiraliyev.dto.PersonDto;
import com.rabilmiraliyev.model.Adress;
import com.rabilmiraliyev.model.Person;
import com.rabilmiraliyev.repository.AdressRepository;
import com.rabilmiraliyev.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final AdressRepository adressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
     //   Assert.isNull(personDto.getFisrtname(),"Firstname Empty");

        Person person = new Person();
        person.setFisrtname(personDto.getFirstname());
        person.setLastname(personDto.getLastname());
        final Person personDb = personRepository.save(person);
///
        List<Adress> list = new ArrayList<>();
        personDto.getAdresses().forEach(item -> {
            Adress adress = new Adress();
            adress.setAdres(item);
            adress.setAdresType(Adress.AdresType.OTHER);
            adress.setStatus(true);
            adress.setPerson(personDb);
        });
        adressRepository.saveAll(list);
//        personDto.setId(personDb.getId());
//        personRepository.save(personDb);
        return personDto;
    }

    @Override
    public void delete() {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        personList.forEach(item -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(item.getId());
            personDto.setFirstname(item.getFisrtname());
            personDto.setLastname(item.getLastname());
            personDto.setAdresses(item.getAdresses().stream().map(Adress::getAdres)
                    .collect(Collectors.toList()));
            personDtos.add(personDto);
        });
        return personDtos;
    }


    @Override
    public Page<Person> getAll(Pageable pageable) {
        return null;
    }
}
