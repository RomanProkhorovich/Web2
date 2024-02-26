package com.example.Web2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    @Builder.Default
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate = new Date();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "project", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks = new LinkedHashSet<>();

}
