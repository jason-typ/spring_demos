package com.example.demo.dao;

import com.example.demo.domain.Employee;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDAO {
    private static String url = "jdbc:mysql://127.0.0.1:3306/db_example";
    private static String username = "tang";
    private static String password = "1234";
    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            createEmployeeTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEmployeeTable() {
        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                statement.executeUpdate(
                        "create table if not exists Employee(empId VARCHAR(20), name VARCHAR(50), designation VARCHAR(50),salary VARCHAR(50))"
                );
            } finally {
                if (conn != null)
                    conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewEmployee(Employee employee) {
        try {
            Connection conn = null;
            PreparedStatement insertEmployee = null;
            try {
                conn = DriverManager.getConnection(url, username, password);
                insertEmployee = conn.prepareStatement("insert into employee (empid, name, designation) values (?, ?, ?)");
                insertEmployee.setString(1, employee.getEmpId());
                insertEmployee.setString(2, employee.getName());
                insertEmployee.setString(3, employee.getDesignation());
                insertEmployee.executeUpdate();
                System.out.println("inserted new employee");
            } finally {
                if (conn != null)
                    conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> results = new ArrayList<Employee>();
        try {
            Connection con = null;
            PreparedStatement retrieveBooks = null;
            ResultSet rs = null;

            try {
                con = DriverManager
                        .getConnection(url, username, password);
                retrieveBooks = con.prepareStatement("select * from Employee");
                rs = retrieveBooks.executeQuery();
                while (rs.next()) {
                    String empId = rs.getString(1);
                    String name = rs.getString(2);
                    String designation = rs.getString(3);
                    double salary = 0;
                    Employee nextBook = new Employee(empId, name, designation,
                            salary);
                    results.add(nextBook);
                }

            } finally {
                if (rs != null)
                    rs.close();
                if (con != null)
                    con.close();
            }
        } catch (SQLException e) {
            System.out.println("Exception Occured");
        }
        return results;
    }
}
