package com.example.intro.primary;

import org.springframework.stereotype.Service;

@Service
public class CustomerBServiceImpl implements CustomerService {
    @Override
    public String sayHello() {
        return "Hello from customer B";
    }
}
