package com.example.Web2.repository;

import com.example.Web2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JDBCTaskRepository extends JpaRepository<Task, Long> {

    @Query("from Task t where t.project.id = ?1 and t.id = ?2")
    Task findByProjectIdAndTaskId(Long projectId, Long id);

    @Query("from Task t where t.project.id = ?1")
    List<Task> findByProjectId(Long projectId);

    @Modifying
    @Query("delete from Task t where t.project.id = :projectId " +
            "and isEnded = true ")
    void deleteEndedByProjectId(Long projectId);

}
