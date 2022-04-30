package com.rabilmiraliyev.repository;

import com.rabilmiraliyev.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
