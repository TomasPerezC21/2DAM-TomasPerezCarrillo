package com.example.intro.controller;


import com.example.intro.primary.CustomerService;
import com.example.intro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    public String sayHelloFromEmployeeService(){
        return this.employeeService.sayHello();
    }

    public String sayHelloFromCustomerService(){
        return this.customerService.sayHello();
    }


}
