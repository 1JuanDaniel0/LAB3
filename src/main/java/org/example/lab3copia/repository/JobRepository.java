package org.example.lab3copia.repository;

import org.example.lab3copia.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}