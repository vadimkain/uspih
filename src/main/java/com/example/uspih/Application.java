package com.example.uspih;

import com.example.uspih.domain.Role;
import com.example.uspih.domain.User;
import com.example.uspih.repos.UserRepo;
import com.example.uspih.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
