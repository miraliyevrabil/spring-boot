package com.rabilmiraliyev.graphql.repo;

import java.util.List;

import com.rabilmiraliyev.graphql.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getByType(String type);

}