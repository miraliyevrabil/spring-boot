package com.rabilmiraliyev.graphql.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rabilmiraliyev.graphql.model.Task;
import com.rabilmiraliyev.graphql.model.dto.TaskDto;
import com.rabilmiraliyev.graphql.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TaskMutationResolver implements GraphQLMutationResolver {

    private final TaskRepository taskRepository;

    public Task createTask(TaskDto taskDto) {
        return taskRepository.save(dtoToEntity(taskDto));
    }

    private Task dtoToEntity(TaskDto taskDto){
        Task task=new Task();
        task.setDescription(taskDto.getDescription());
        task.setDate(new Date());
        task.setType(taskDto.getType());
        return task;
    }
}