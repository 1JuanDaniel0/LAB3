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

    // Lista todos los empleados
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Filtro para buscar por nombre de empleado
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    // Para buscar por departamento
    public List<Employee> searchEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    // Para obtener losd atos del empleado por su id
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Para actualizar el empleado
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}