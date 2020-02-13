package com.example.demo.controller;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeDataController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/add")
    public String add(@RequestParam String empId, @RequestParam String name, @RequestParam String desination) {
        Employee employee = new Employee(empId, name, desination, 100);
        employeeService.addEmployee(employee);

        return "new employee added";
    }

    @RequestMapping("/list")
    public @ResponseBody
    List<Employee> list() {
        return employeeService.getEmployees();
    }
}
