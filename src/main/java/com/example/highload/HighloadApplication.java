package com.example.highload;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HighloadApplication {
    public static void main(String[] args) {
        SpringApplication.run(HighloadApplication.class, args);
        System.out.println("Test");
    }
}


