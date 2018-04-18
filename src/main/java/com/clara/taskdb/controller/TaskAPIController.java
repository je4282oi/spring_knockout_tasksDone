package com.clara.taskdb.controller;

import com.clara.taskdb.model.Task;
import com.clara.taskdb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskAPIController {
    private final TaskRepository tasks;

    @Autowired
    public TaskAPIController(TaskRepository tasks){
        this.tasks = tasks;

        //Exmaple tasks.
        tasks.save(new Task("task 1", true, false));
        tasks.save(new Task("task 2", false, true));
        tasks.save(new Task("task 3", true, false));
        tasks.save(new Task("task 4", false, false));
    }

    //Add a task typed into text field
    //Post route = create a resource
    @PostMapping(value ="/add")
    public ResponseEntity addTask(@RequestBody Task task) {
        System.out.println("new task: " + task);
        try {
            tasks.save(task);
        }catch (Exception e){
            return new ResponseEntity<>("task object is invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
    //Route for getting all the tasks
    //Get route = get a resource
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> queryTasks(){
        return new ResponseEntity<>(tasks.findAllByOrderByUrgentDesc(), HttpStatus.OK);
    }

    //Route for showing completed tasks
    //Patch route = update part of resource
    @PatchMapping(value= "/completed")
    public ResponseEntity markTaskAsCompleted(@RequestBody Task task) {
        int tasksUpdated = tasks.setTaskCompleted(task.isCompleted(), task.getId());
        if (tasksUpdated ==0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND); //Error, task not in DB
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //Route for deleting tasks (if task not present does nothing)
    //Delete route = delete a resource
    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteTask(@RequestBody Task task) {
        tasks.delete(task);
        return new ResponseEntity(HttpStatus.OK);
    }
}