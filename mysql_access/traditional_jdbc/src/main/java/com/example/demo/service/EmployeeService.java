package com.example.demo.service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public void addEmployee(Employee emp) {
        employeeDAO.addNewEmployee(emp);
    }

    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
