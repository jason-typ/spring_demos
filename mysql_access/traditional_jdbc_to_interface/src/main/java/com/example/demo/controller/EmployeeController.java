package com.example.demo.controller;

import com.example.demo.dao.IEmployeeDAO;
import com.example.demo.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeDAO employeeDAO;

    @RequestMapping("/add")
    public String add(@RequestParam String id, @RequestParam String name) {
        Employee employee = new Employee(id, name, "test", 1000);
        employeeDAO.addNewEmployee(employee);
        return "saved";
    }

    @RequestMapping("/list")
    public @ResponseBody List<Employee> list() {
        return employeeDAO.getAllEmployee();
    }
}
