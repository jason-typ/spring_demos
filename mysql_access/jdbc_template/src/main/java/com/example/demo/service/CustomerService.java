package com.example.demo.service;

import com.example.demo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        jdbcTemplate.execute("DROP TABLE  IF EXISTS customers");
        jdbcTemplate.execute("create table customers(id int not null auto_increment, name varchar(255), email varchar(255), primary key (id));");
    }

    public void add(String name, String email) {
        String[] arg = new String[]{name, email};
        jdbcTemplate.update("insert into customers(name, email) values (?, ?)", arg);
    }

    public List<String> list() {
        List<String> customers = jdbcTemplate.query("select id, name, email from customers", row -> {
            List<Customer> ret = new ArrayList<>();
            while (row.next()) {
                ret.add(new Customer(row.getLong("id"), row.getString("name"), row.getString("email")));
            }
            return ret;
        }).stream().map(Customer::toString).collect(Collectors.toList());

        return customers;
    }
}
