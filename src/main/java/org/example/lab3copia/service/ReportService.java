package org.example.lab3copia.service;

import org.example.lab3copia.model.Employee;
import org.example.lab3copia.model.Report;
import org.example.lab3copia.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Report> generateReports(String nameFilter) {
        List<Employee> employees = employeeRepository.findAll();

        // Aplicar filtro por nombre si existe
        if (nameFilter != null && !nameFilter.isEmpty()) {
            employees = employees.stream()
                    .filter(emp -> (emp.getFirstName() != null && emp.getFirstName().toLowerCase().contains(nameFilter.toLowerCase())) ||
                            (emp.getLastName() != null && emp.getLastName().toLowerCase().contains(nameFilter.toLowerCase())))
                    .collect(Collectors.toList());
        }

        List<Report> reports = new ArrayList<>();
        if (!employees.isEmpty()) {
            // Calcular salario máximo
            Double salarioMax = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .max()
                    .orElse(0.0);

            // Calcular salario mínimo
            Double salarioMin = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .min()
                    .orElse(0.0);

            // Calcular salario promedio por trabajo
            var promPorTrab = employees.stream()
                    .collect(Collectors.groupingBy(
                            emp -> emp.getJob() != null ? emp.getJob().getJobTitle() : "Sin puesto",
                            Collectors.averagingDouble(Employee::getSalary)
                    ));
            Double salarioPromPorTrabajo = promPorTrab.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            // Nombre del empleado con salario más alto
            String highestPaidName = employees.stream()
                    .filter(emp -> emp.getSalary() != null && emp.getSalary().equals(salarioMax))
                    .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                    .findFirst()
                    .orElse("N/A");

            reports.add(new Report(salarioMax, salarioMin, salarioPromPorTrabajo, highestPaidName));
        }

        return reports;
    }
}