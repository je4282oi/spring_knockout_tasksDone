package com.clara.taskdb.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1)
    private String text;

    private boolean urgent;
    private boolean completed = false;

    public Task() {
        //Empty contructor needed by Json
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", urgent=" + urgent +
                ", completed=" + completed +
                '}';
    }
}