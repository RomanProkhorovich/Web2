package com.example.Web2.dto;

import com.example.Web2.model.Project;
import com.example.Web2.model.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date finalDate;
    private Boolean isEnded = false;
    @JsonIgnore
    private Long projectId;

    public static TaskDto of(Task task) {
        TaskDto dto = TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .finalDate(task.getFinalDate())
                .isEnded(task.getIsEnded())
                .projectId(task.getProject().getId())
                .name(task.getName())
                .build();
        return dto;
    }

    public static List<TaskDto> of(List<Task> task) {
        return task.stream()
                .map(TaskDto::of)
                .collect(Collectors.toList());
    }

    public Task toTaskWithoutProject(){
        Task task = Task.builder()
                .name(name)
                .description(description)
                .finalDate(finalDate)
                .isEnded(isEnded)
                .id(id)
                .build();
        return task;
    }
}
