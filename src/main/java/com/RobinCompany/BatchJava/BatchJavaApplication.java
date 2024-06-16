package com.RobinCompany.BatchJava;

import com.RobinCompany.BatchJava.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchJavaApplication implements CommandLineRunner {

    // Projet réalisé par Robin Replat et Dorian Rousseau

    @Autowired
    FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(BatchJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileService.processFiles();
    }
}
