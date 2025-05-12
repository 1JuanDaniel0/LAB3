package org.example.lab3copia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name = "job_id")
    private String id;

    @Column(name = "job_title")
    private String jobTitle;

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}