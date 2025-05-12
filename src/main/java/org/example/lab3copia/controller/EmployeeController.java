package org.example.lab3copia.controller;

import org.example.lab3copia.model.Department;
import org.example.lab3copia.model.Employee;
import org.example.lab3copia.model.Job;
import org.example.lab3copia.model.Report;
import org.example.lab3copia.service.DepartmentService;
import org.example.lab3copia.service.EmployeeService;
import org.example.lab3copia.service.JobService;
import org.example.lab3copia.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/employeeList")
    public String getEmployeeList(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Department> departments = departmentService.getAllDepartments();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "employeeList";
    }

    // Incluye la lógica de filtros
    @PostMapping("/employeeList/search")
    public String searchEmployees(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "department", required = false) String department,
            Model model) {
        List<Employee> employees;
        if (name != null && !name.isEmpty()) {
            employees = employeeService.searchEmployeesByName(name);
        } else if (department != null && !department.isEmpty()) {
            employees = employeeService.searchEmployeesByDepartment(department);
        } else {
            employees = employeeService.getAllEmployees();
        }

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "employeeList";
    }

    // Metodo para editar un empleado por su id
    @GetMapping("/employee/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return "redirect:/employeeList"; // Redirige si no se encuentra el empleado
        }

        List<Department> departments = departmentService.getAllDepartments();
        List<Job> jobs = jobService.getAllJobs();

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("jobs", jobs);
        return "editEmployee";
    }

    // Metodo para mostrar y actualizar los datos del empleado
    @PostMapping("/employee/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(employee.getId());
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setSalary(employee.getSalary());

            Job job = jobService.getJobById(employee.getJob().getId());
            existingEmployee.setJob(job);

            Department department = departmentService.getAllDepartments().stream()
                    .filter(dept -> dept.getId().equals(employee.getDepartment().getId()))
                    .findFirst()
                    .orElse(null);
            existingEmployee.setDepartment(department);

            employeeService.updateEmployee(existingEmployee);
        }
        return "redirect:/employeeList";
    }

    // MEtodo para obtener la lista de reportes
    @GetMapping("/reportList")
    public String getReportList(Model model) {
        List<Report> reports = reportService.generateReports(null);
        model.addAttribute("reports", reports);
        return "reportList";
    }

    // para buscar en la lista de reportes
    @PostMapping("/reportList/search")
    public String searchReports(
            @RequestParam(value = "name", required = false) String name,
            Model model) {
        List<Report> reports = reportService.generateReports(name);
        model.addAttribute("reports", reports);
        return "reportList";
    }
}