package com.RobinCompany.BatchJava.service;

import com.RobinCompany.BatchJava.model.User;
import com.RobinCompany.BatchJava.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }
}
