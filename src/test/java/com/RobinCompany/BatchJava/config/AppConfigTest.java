package com.RobinCompany.BatchJava.config;

import com.RobinCompany.BatchJava.service.FileService;
import com.RobinCompany.BatchJava.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(AppConfig.class)
@ActiveProfiles("test")
public class AppConfigTest {

    /**
     * Ce test vérifie que les beans UserRepository et FileService sont correctement configurés
     * et disponibles dans le contexte Spring.
     */
    @Test
    public void testAppConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        FileService fileService = context.getBean(FileService.class);

        assertNotNull(userRepository);
        assertNotNull(fileService);
    }
}
