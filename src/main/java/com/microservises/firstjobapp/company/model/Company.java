package com.microservises.firstjobapp.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservises.firstjobapp.job.model.Job;
import com.microservises.firstjobapp.review.model.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
}
