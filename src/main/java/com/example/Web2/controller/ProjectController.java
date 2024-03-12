package com.example.Web2.controller;


import com.example.Web2.dto.ProjectDto;
import com.example.Web2.model.Project;
import com.example.Web2.service.ProjectService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.invoker.UrlArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService service;
    @PostMapping
    public ResponseEntity<ProjectDto> createEmpty(@RequestBody ProjectDto dto,
                                               UriComponentsBuilder uriComponentsBuilder){

        Project createdProject = service.create(dto.toProject());
        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/projects/{projectId}")
                        .build(Map.of("projectId",createdProject.getId())))
                .body(ProjectDto.of(createdProject));
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProject(@PathVariable("projectId") Long projectId){
        return ProjectDto.of(service.findById(projectId));
    }

    @PutMapping("/{projectId}")
    public Project update(@PathVariable("projectId") Long projectId,
                          @RequestBody ProjectDto project){
        return service.update(project.toProject(), projectId);
    }

    @DeleteMapping("/{projectId}")
    public void delete(@PathVariable("projectId") Long projectId){
        service.delete(projectId);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> search(@RequestParam(value = "search", required = false) String query){
        if (StringUtils.isBlank(query))
            return ResponseEntity.ok(ProjectDto.of(service.findAll()));

        return ResponseEntity.ok(ProjectDto.of(service.findByFilter(query)));
    }

    @GetMapping("/notEnded")
    public ResponseEntity<Map<Long, Long>> getMap(){
        return ResponseEntity.ok(service.getCountAndId());
    }

}