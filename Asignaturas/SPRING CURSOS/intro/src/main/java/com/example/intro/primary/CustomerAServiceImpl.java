package com.example.intro.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CustomerAServiceImpl implements CustomerService {
    @Override
    public String sayHello() {
        return "Hello from customer A";
    }
}
