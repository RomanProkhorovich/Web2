package com.example.Web2.controller;


import com.example.Web2.model.Project;
import com.example.Web2.service.ProjectService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService service;
    @PostMapping
    public void createEmpty(@RequestBody Project project){
        service.create(project);
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable("projectId") Long projectId){
        return service.findById(projectId);
    }

    @PutMapping("/{projectId}")
    public Project update(@PathVariable("projectId") Long projectId,
                          @RequestBody Project project){
        return service.update(project, projectId);
    }

    @DeleteMapping("/{projectId}")
    public void delete(@PathVariable("projectId") Long projectId){
        service.delete(projectId);
    }

    @GetMapping
    public ResponseEntity<List<Project>> search(@RequestParam(value = "search", required = false) String query){
        if (StringUtils.isBlank(query))
            return ResponseEntity.ok(service.findAll());

        return ResponseEntity.ok(service.findByFilter(query));
    }

    @GetMapping("/notEnded")
    public ResponseEntity<Map<Long,Long>> getMap(){
        return ResponseEntity.ok(service.getCountAndId());
    }
//TODO Добавить возможность получить информацию о количестве незакрытых задач во всех проектах.
// Информация должна быть возвращена в виде ассоциативного массива следующего вида:
// Ключ - идентификатор проекта. Значение - количество незакрытых задач.
// Для проекта без открытых задач должен быть возвращён ноль.

}