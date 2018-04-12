package com.clara.taskdb.controller;

import com.clara.taskdb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskAPIController {
    private final TaskRepository tasks;

    @Autowired
    public TaskAPIController(TaskRepository tasks){
        this.tasks = tasks;

        //Exmaple tasks.

    }
}