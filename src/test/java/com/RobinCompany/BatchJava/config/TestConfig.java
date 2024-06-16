package com.RobinCompany.BatchJava.config;

import com.RobinCompany.BatchJava.service.FileServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public FileServiceImpl fileService() {
        return Mockito.mock(FileServiceImpl.class);
    }
}
