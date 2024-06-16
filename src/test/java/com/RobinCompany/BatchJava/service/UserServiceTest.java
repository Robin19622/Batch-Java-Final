package com.RobinCompany.BatchJava.service;

import com.RobinCompany.BatchJava.dao.UserRepository;
import com.RobinCompany.BatchJava.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrUpdateUser() {
        User user = new User();
        userService.saveOrUpdateUser(user);
        verify(userRepository).save(any(User.class));
    }
}
