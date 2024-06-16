package com.RobinCompany.BatchJava.util;

import com.RobinCompany.BatchJava.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomCSVParserTest {

    private CustomCSVParser customCSVParser;

    @BeforeEach
    public void setUp() {
        customCSVParser = new CustomCSVParser();
    }

    @Test
    public void testParse() throws IOException {
        File tempFile = File.createTempFile("users_20230615093000", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("123-45-6789,Doe,John,1980-01-01,555-1234,john.doe@example.com,12345,101,200.00\n");
        }

        List<User> users = customCSVParser.parse(tempFile);

        assertNotNull(users);
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("123-45-6789", user.getSocialSecurityNumber());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("1980-01-01", user.getBirthDate());
        assertEquals("555-1234", user.getPhoneNumber());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("12345", user.getReimbursementId());
        assertEquals("101", user.getCareCode());
        assertEquals("200.00", user.getReimbursementAmount());
        assertEquals("20230615093000", user.getFileTimestamp());

        tempFile.delete();
    }
}
