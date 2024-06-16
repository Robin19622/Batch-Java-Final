package com.RobinCompany.BatchJava.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setReimbursementId("12345");
        user.setSocialSecurityNumber("123-45-6789");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setBirthDate("1980-01-01");
        user.setPhoneNumber("555-1234");
        user.setEmail("john.doe@example.com");
        user.setCareCode("101");
        user.setReimbursementAmount("200.00");
        user.setFileTimestamp("20230615093000");

        assertEquals("12345", user.getReimbursementId());
        assertEquals("123-45-6789", user.getSocialSecurityNumber());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("1980-01-01", user.getBirthDate());
        assertEquals("555-1234", user.getPhoneNumber());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("101", user.getCareCode());
        assertEquals("200.00", user.getReimbursementAmount());
        assertEquals("20230615093000", user.getFileTimestamp());
    }
}
