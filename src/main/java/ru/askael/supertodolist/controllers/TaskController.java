package ru.askael.supertodolist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Alikin E.A. on 2020-09-27.
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @GetMapping
    public List<String> getAllTasks() {
        return List.of("task1", "task2");
    }
}
