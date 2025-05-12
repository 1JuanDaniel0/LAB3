package org.example.lab3copia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "department_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}