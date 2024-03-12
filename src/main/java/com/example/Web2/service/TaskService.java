package com.example.Web2.service;

import com.example.Web2.model.Task;
import com.example.Web2.repository.JDBCTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final JDBCTaskRepository repository;

    public Task findByProjectIdAndTaskId(Long projectId, Long id){
        return repository.findByProjectIdAndTaskId(projectId,id);
    }

    public List<Task> findAll(){
        return repository.findAll();
    }

    public Task create(Task task){
        return repository.save(task);
    }

    public List<Task> findByProject(Long id){
        return repository.findByProjectId(id);
    }


    public Task update(Task task, Long id) {
        task.setId(id);
        return repository.save(task);
    }

    public void delete(Long projectId, Long taskId){
        repository.deleteProjectIdAndTaskId(projectId,taskId);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void deleteClosedByProject(Long projectId){
        repository.deleteEndedByProjectId(projectId);
    }
}
