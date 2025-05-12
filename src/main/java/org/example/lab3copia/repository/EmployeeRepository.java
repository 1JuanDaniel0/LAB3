package org.example.lab3copia.repository;

import org.example.lab3copia.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Query para la bsúsqueda por nombre
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> findByNameContaining(String name);

    // para la busqueda por departamento
    List<Employee> findByDepartmentName(String departmentName);
}