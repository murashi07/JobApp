package com.microservises.firstjobapp.company.repository;

import com.microservises.firstjobapp.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long > {
}
