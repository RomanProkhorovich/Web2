package com.example.Web2.service;

import com.example.Web2.exception.EntityNotFoundException;
import com.example.Web2.model.Project;
import com.example.Web2.repository.JDBCProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private JDBCProjectRepository repository;

    public Project create(Project project) {
        return repository.save(project);
      /*  try {
            return repository.insert(project);
        }
        catch (DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }*/
    }

    public Project update(Project project, Long projectId) {
        project.setId(projectId);
        return repository.save(project);
    }

    public Project findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
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
        return findAll().stream()
                .collect(Collectors.toMap(
                        Project::getId,
                        y -> y.getTasks().stream()
                                .filter(z -> !z.getIsEnded())
                                .count())
                );
    }
}
