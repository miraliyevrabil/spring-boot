package com.rabilmiraliyev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}
