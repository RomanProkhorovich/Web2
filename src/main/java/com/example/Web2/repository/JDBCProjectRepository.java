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
import java.util.Objects;

@Repository
@Transactional
public interface JDBCProjectRepository extends JpaRepository<Project,Long> {
    @Query("select distinct project from Task t where " +
            "upper(t.name) like upper(concat('%', :query,'%'))" +
            "or upper(t.description) like upper(concat('%', :query ,'%'))")
    List<Project> filter (@Param("query") String query);

    @Query( "select pr.id as id, " +
            "(select count(*) " +
            " from Task t where t.isEnded = false" +
            " and t.project.id=pr.id ) as count " +
            "from Project pr")
    List<Map<String, Object>> getMap();
}
