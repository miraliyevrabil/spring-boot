package com.rabilmiraliyev.graphql.controller;

import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rabilmiraliyev.graphql.model.Task;

import com.rabilmiraliyev.graphql.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskQueryResolver implements GraphQLQueryResolver {

    private final TaskRepository taskRepository;

    public List<Task> getTask(String type) {
        return taskRepository.getByType(type);
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }
}