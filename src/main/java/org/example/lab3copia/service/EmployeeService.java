package org.example.lab3copia.service;

import org.example.lab3copia.model.Employee;
import org.example.lab3copia.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    public List<Employee> searchEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }
}