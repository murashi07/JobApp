package com.microservises.firstjobapp.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservises.firstjobapp.company.model.Company;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
