package com.example.spring.controller;

import com.example.spring.model.Employee;
import com.example.spring.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam int departmentId) {
        return service.findMaxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee findMinSalary(@RequestParam int departmentId) {
        return service.findMinSalary(departmentId);

    }
    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> findAllByDepartment(@RequestParam int departmentId) {
        return service.findByDepartment(departmentId);

    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartment();
    }
}
