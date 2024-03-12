package com.example.Web2.controller;

import com.example.Web2.dto.TaskDto;
import com.example.Web2.model.Task;
import com.example.Web2.service.ProjectService;
import com.example.Web2.service.TaskService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
@AllArgsConstructor
public class TaskController {

    private final ProjectService projectService;
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getByProject(@RequestParam("projectId") Long projectId) {
        List<Task> projects = service.findByProject(projectId);
        return ResponseEntity.ok(TaskDto.of(projects));
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getByTaskIdAndProjectId(@RequestParam("projectId") Long projectId,
                                                           @RequestParam("taskId") Long taskId){
        Task task = service.findByProjectIdAndTaskId(projectId, taskId);
        return  ResponseEntity.ok(TaskDto.of(task));
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto task,
                                       @RequestParam("projectId") Long projectId,
                                       UriComponentsBuilder uriComponentsBuilder){
        Task taskWithoutProject = task.toTaskWithoutProject();
        taskWithoutProject.setProject(projectService.findById(projectId));
        taskWithoutProject = service.create(taskWithoutProject);

        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/projects/{projectId}/tasks/{taskId}")
                        .build(Map.of("projectId", taskWithoutProject.getProject(),
                                "taskId", taskWithoutProject.getId())))
                .body(TaskDto.of(taskWithoutProject));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> update(@RequestParam("taskId") Long taskId,
                                          @RequestBody TaskDto task,
                                          @RequestParam("projectId") Long projectId){
        Task taskWithoutProject = task.toTaskWithoutProject();
        taskWithoutProject.setProject(projectService.findById(projectId));
        Task updated = service.update(taskWithoutProject, taskId);

        return ResponseEntity.ok(TaskDto.of(updated));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@RequestParam("taskId") Long taskId,
                                       @RequestParam("projectId") Long projectId){
        service.delete(projectId, taskId);
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/notEnded")
    public ResponseEntity<?> deleteAllNotClosed(@RequestParam("projectId") Long projectId) {
        service.deleteClosedByProject(projectId);
        return ResponseEntity.ok("task.deleted.successfully");
    }
}
