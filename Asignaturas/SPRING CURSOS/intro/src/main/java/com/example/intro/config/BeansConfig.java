package com.example.intro.config;

import com.example.intro.primary.CustomerAServiceImpl;
import com.example.intro.primary.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

public class BeansConfig {

    /*
    Esta opcion se utiliza para configurar clases que provienen de frameworks externos
     */
    @Bean
    public CustomerService customerService(){
        return new CustomerAServiceImpl();
    }

}
