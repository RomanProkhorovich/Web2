package com.example.Web2.dto;

import com.example.Web2.model.Project;
import com.example.Web2.model.Task;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date finalDate;
    private Boolean isEnded = false;
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

}
