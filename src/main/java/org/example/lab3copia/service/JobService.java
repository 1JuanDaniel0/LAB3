package org.example.lab3copia.service;

import org.example.lab3copia.model.Job;
import org.example.lab3copia.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    // Para buscar todos los trabajos
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Por id
    public Job getJobById(String id) {
        return jobRepository.findById(id).orElse(null);
    }
}