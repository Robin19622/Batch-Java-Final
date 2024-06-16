package com.RobinCompany.BatchJava.service;

import com.RobinCompany.BatchJava.dao.UserRepository;
import com.RobinCompany.BatchJava.model.User;
import com.RobinCompany.BatchJava.util.CustomCSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final UserRepository userRepository;
    private final CustomCSVParser csvParser;

    private String inputDirectory = "input";
    private String processedDirectory = "processed";

    @Autowired
    public FileServiceImpl(UserRepository userRepository, CustomCSVParser csvParser) {
        this.userRepository = userRepository;
        this.csvParser = csvParser;
    }

    public void setInputDirectory(String inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    public void setProcessedDirectory(String processedDirectory) {
        this.processedDirectory = processedDirectory;
    }

    @Override
    public void processFiles() {
        File folder = new File(inputDirectory);
        File[] files = folder.listFiles((dir, name) -> name.matches("users_\\d{14}\\.csv"));

        if (files != null) {
            Arrays.sort(files, Comparator.comparing(File::getName));

            for (File file : files) {
                try {
                    List<User> users = csvParser.parse(file);
                    userRepository.saveAll(users);
                    Files.move(file.toPath(), Paths.get(processedDirectory, file.getName()));
                } catch (IOException e) {
                    // Replace with a logging framework
                    e.printStackTrace();
                }
            }
        }

        if (folder.exists() && folder.isDirectory()) {
            File[] remainingFiles = folder.listFiles();
            if (remainingFiles != null && remainingFiles.length == 0) {
                System.out.println("Le dossier est vide mais ne sera pas supprimé.");
            }
        }
    }
}
