package com.RobinCompany.BatchJava.util;

import com.RobinCompany.BatchJava.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomCSVParser {

    public List<User> parse(File file) throws IOException {
        List<User> users = new ArrayList<>();
        try (FileReader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                User user = new User();
                user.setSocialSecurityNumber(record.get(0));
                user.setLastName(record.get(1));
                user.setFirstName(record.get(2));
                user.setBirthDate(record.get(3));
                user.setPhoneNumber(record.get(4));
                user.setEmail(record.get(5));
                user.setReimbursementId(record.get(6));
                user.setCareCode(record.get(7));
                user.setReimbursementAmount(record.get(8));
                String timestamp = extractTimestamp(file.getName());
                user.setFileTimestamp(timestamp);
                users.add(user);
            }
        }
        return users;
    }

    private String extractTimestamp(String fileName) {
        String[] parts = fileName.split("_");
        if (parts.length > 1) {
            String timestampPart = parts[1];
            return timestampPart.substring(0, 14);
        }
        return null;
    }
}
