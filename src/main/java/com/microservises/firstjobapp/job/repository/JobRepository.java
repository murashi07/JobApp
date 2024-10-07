package com.microservises.firstjobapp.job.repository;

import com.microservises.firstjobapp.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
