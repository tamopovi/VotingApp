package com.votingapp.votingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VotingappApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VotingappApplication.class, args);
    }
}
