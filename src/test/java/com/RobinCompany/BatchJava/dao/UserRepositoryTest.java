package com.RobinCompany.BatchJava.dao;

import com.RobinCompany.BatchJava.config.AppConfig;
import com.RobinCompany.BatchJava.config.TestConfig;
import com.RobinCompany.BatchJava.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {AppConfig.class, TestConfig.class})
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
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

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findById("12345");
        assertTrue(foundUser.isPresent());
        assertEquals("John", foundUser.get().getFirstName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setReimbursementId("54321");
        user.setSocialSecurityNumber("987-65-4321");
        user.setLastName("Smith");
        user.setFirstName("Jane");
        user.setBirthDate("1990-01-01");
        user.setPhoneNumber("555-4321");
        user.setEmail("jane.smith@example.com");
        user.setCareCode("102");
        user.setReimbursementAmount("300.00");
        user.setFileTimestamp("20230615103000");

        userRepository.save(user);

        userRepository.deleteById("54321");

        Optional<User> deletedUser = userRepository.findById("54321");
        assertFalse(deletedUser.isPresent());
    }
}
