package com.example.intro;

import com.example.intro.controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(App.class, args);


       EmployeeController controller = context.getBean(EmployeeController.class);
       System.out.println(controller.sayHelloFromEmployeeService());
       System.out.println(controller.sayHelloFromCustomerService());

    }

}
