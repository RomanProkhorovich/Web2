package com.example.Web2.config;

import com.example.Web2.model.Project;
import com.example.Web2.model.Task;
import com.example.Web2.service.ProjectService;
import com.example.Web2.service.TaskService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Builder
@RequiredArgsConstructor
@Configuration
public class InsertData implements ApplicationRunner {
    private final ProjectService projectService;
    private final TaskService taskService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Project pr = Project.builder()
                .name("проект")
                .description("описание")
                .build();
        pr = projectService.create(pr);
        Task task = Task.builder()
                .description("описание задачи")
                .name("название задачи")
                .project(pr)
                .build();
        task= taskService.create(task);

        Project pr1 = Project.builder()
                .name("проект2")
                .description("описание2")
                .build();
        pr1 = projectService.create(pr1);
        Task task1 = Task.builder()
                .description("тест")
                .name("тест")
                .project(pr1)
                .build();
        task1= taskService.create(task1);

        Project pr2 = Project.builder().name("empty").build();
        projectService.create(pr2);

    }
}
