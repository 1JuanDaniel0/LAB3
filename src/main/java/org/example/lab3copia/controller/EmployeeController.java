package org.example.lab3copia.controller;

import org.example.lab3copia.model.Department;
import org.example.lab3copia.model.Employee;
import org.example.lab3copia.service.DepartmentService;
import org.example.lab3copia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employeeList")
    public String getEmployeeList(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Department> departments = departmentService.getAllDepartments();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "employeeList";
    }

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
}