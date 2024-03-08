package com.example.Web2.controller;

import com.example.Web2.model.Task;
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
    public ResponseEntity<List<Task>> getByProject(@PathParam("projectId") Long projectId) {
        return ResponseEntity.ok(service.findByProject(projectId));
    }


    @DeleteMapping("/notEnded")
    public ResponseEntity<?> deleteAllNotClosed(@RequestParam("projectId") Long projectId) {
        service.deleteClosedByProject(projectId);
        return ResponseEntity.ok("task.deleted.successfully");
    }
}
