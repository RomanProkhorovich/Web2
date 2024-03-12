package com.example.Web2.service;

import com.example.Web2.exception.EntityNotFoundException;
import com.example.Web2.model.Project;
import com.example.Web2.repository.JDBCProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private JDBCProjectRepository repository;
    @Autowired
    private MessageSource messageSource;

    public Project create(Project project) {
        return repository.save(project);
    }

    public Project update(Project project, Long projectId) {
        project.setId(projectId);
        return repository.save(project);
    }

    public Project findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("project.notFound.byId",
                                new Object[]{id},
                                Locale.ROOT),
                        id));
    }

    public void delete(Long projectId) {
        repository.deleteById(projectId);
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public List<Project> findByFilter(String query) {
        return repository.filter(query);
    }


    public Map<Long, Long> getCountAndId() {
        var a = repository.getMap();
        Map<Long, Long> collect = a.stream()
                .collect(
                        Collectors.toMap(x -> (Long) (x.get("id")),
                                x -> (Long) (x.get("count"))));
        return collect;
    }
}
