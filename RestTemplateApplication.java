package com.example.resttemplate;

import com.example.resttemplate.model.User;
import com.example.resttemplate.service.Communication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestTemplateApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(RestTemplateApplication.class, args);
        Communication communication = context.getBean(Communication.class);
        
        User firstUser = new User(3L, "James", "Brown", (byte) 25);
        User secondUser = new User(3L, "Thomas", "Shelby", (byte) 27);

        String cookies = communication.getAll();

        StringBuilder result = new StringBuilder();
        result.append(communication.save(firstUser, cookies));
        result.append(communication.update(secondUser, cookies));
        result.append(communication.delete(3L, cookies));

        System.out.println(result);


    }

}
