package com.rabilmiraliyev.repository;

import com.rabilmiraliyev.model.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {
    @Query("{\"bool\": " +
            "{\"must\": " +
            "[{\"match\": " +
            "{\"surname\": \"?0\"}}]" +
            "}}")
    List<Person> findByCustomQuery(String name);

    @Query("{\"multi_match\" : " +
            "{\"query\":    \"this is a test\"," +
            " \"fields\": [ \"id\", \"name\", \"surname\" ] \n" +
            "}}")
    List<Person> findByMultipleMatchFields(String query);

    @Query("{\"bool\" : { " +
            "\"must\" : [ " +
            "{ \"query_string\" : { \"query\" : \"?0\", \"fields\" : [ \"id\" , \"name\" , \"surname\" ] } } " +
            "]}" +
            "}")
    List<Person> findByMultipleFields(String query);

    List<Person> findPersonByIdOrSurnameLike(String id,String surname);

    List<Person> findPersonBySurname(String surname);

}