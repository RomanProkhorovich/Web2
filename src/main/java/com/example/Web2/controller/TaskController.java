package com.example.Web2.controller;

import com.example.Web2.model.Project;
import com.example.Web2.model.Task;
import com.example.Web2.service.ProjectService;
import com.example.Web2.service.TaskService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> getByProject(@PathParam("projectId") Long projectId){
        return ResponseEntity.ok(service.findByProject(projectId));
    }
    //TODO Добавить функционал, позволяющий удалить все завершённые задачи определённого проекта. Id проекта приходит по HTTP.
}
