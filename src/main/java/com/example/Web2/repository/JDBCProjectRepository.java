package com.example.Web2.repository;

import com.example.Web2
        .model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface JDBCProjectRepository extends JpaRepository<Project,Long> {
    @Query("select distinct project from Task t where " +
            "upper(t.name) like upper(concat('%', :query,'%'))" +
            "or upper(t.description) like upper(concat('%', :query ,'%'))")
    List<Project> filter (@Param("query") String query);

}
