package com.rabilmiraliyev.service;

import com.rabilmiraliyev.dto.PersonDto;
import com.rabilmiraliyev.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {

    PersonDto save(PersonDto personDto);

    void delete();

    List<PersonDto> getAll();

    Page<Person> getAll(Pageable pageable);
}
