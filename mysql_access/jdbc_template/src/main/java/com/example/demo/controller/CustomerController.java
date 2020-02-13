package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/add")
    public String add(@RequestParam String name, @RequestParam String email) {
        customerService.add(name, email);

        return "added success";
    }

    @RequestMapping("/list")
    public @ResponseBody List<String> list() {
        return customerService.list();
    }

    @RequestMapping("/create")
    public void create() {
        customerService.createTable();
    }
}
