package com.example.demo.dao;

import com.example.demo.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface IEmployeeDAO {
    public void addNewEmployee(Employee emp);
    public List<Employee> getAllEmployee();
}
