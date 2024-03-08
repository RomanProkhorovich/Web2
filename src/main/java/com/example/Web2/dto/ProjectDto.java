package com.example.Web2.dto;

import com.example.Web2.model.Project;
import com.example.Web2.model.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate = new Date();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private Set<TaskDto> tasks = new LinkedHashSet<>();


    public static ProjectDto of(Project task) {
        ProjectDto dto = ProjectDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .tasks(task.getTasks().stream()
                        .map(TaskDto::of)
                        .collect(Collectors.toSet()))
                .name(task.getName())
                .build();
        return dto;
    }
}
