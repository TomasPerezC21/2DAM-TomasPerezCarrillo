package com.example.intro.service;


import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public String sayHello() {
        return "hola mundo";
    }
}
