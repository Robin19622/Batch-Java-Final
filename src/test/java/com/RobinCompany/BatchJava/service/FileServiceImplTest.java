package com.RobinCompany.BatchJava.service;

import com.RobinCompany.BatchJava.dao.UserRepository;
import com.RobinCompany.BatchJava.util.CustomCSVParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class FileServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomCSVParser csvParser;

    @InjectMocks
    private FileServiceImpl fileService;

    private Path tempInputDir;
    private Path tempProcessedDir;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        tempInputDir = Files.createTempDirectory("inputTest");
        tempProcessedDir = Files.createTempDirectory("processedTest");

        fileService.setInputDirectory(tempInputDir.toString());
        fileService.setProcessedDirectory(tempProcessedDir.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        try (Stream<Path> paths = Files.walk(tempInputDir)) {
            paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }

        try (Stream<Path> paths = Files.walk(tempProcessedDir)) {
            paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }

        Files.deleteIfExists(tempInputDir);
        Files.deleteIfExists(tempProcessedDir);
    }

    @Test
    void testProcessFilesAndMoveToProcessedDirectory() throws IOException {
        File testFile = new File(tempInputDir.toFile(), "users_20230101000000.csv");
        Files.write(testFile.toPath(), "123456789,Smith,John,1990-01-01,1234567890,john.smith@example.com,1,ABC,1000".getBytes());

        when(csvParser.parse(any(File.class))).thenReturn(Collections.emptyList());

        fileService.processFiles();

        assertFalse(testFile.exists());
        assertTrue(new File(tempProcessedDir.toFile(), "users_20230101000000.csv").exists());
    }
}
