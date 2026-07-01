package com.interviewplatform.health.service;

import com.interviewplatform.health.dto.HealthResponse;
import com.interviewplatform.health.mapping.HealthMapping;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class HealthServiceImpl implements HealthService {

    private final HealthMapping healthMapping;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public HealthResponse checkHealthServer() {
        return healthMapping.getResponse(
                "Server is running",
                LocalDateTime.now()
        );
    }

    @Override
    public HealthResponse checkHealthDatabase() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            return healthMapping.getResponse(
                    "Database connection is healthy",
                    LocalDateTime.now()
            );
        } catch (Exception e) {
            return healthMapping.getResponse(
                    "Database connection failed: " + e.getMessage(),
                    LocalDateTime.now()
            );
        }
    }

    @Override
    public HealthResponse checkHealthSystem() {

        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        String status = String.format(
                "OS: %s | Java: %s | Memory Used: %d MB / %d MB",
                System.getProperty("os.name"),
                System.getProperty("java.version"),
                usedMemory,
                totalMemory
        );

        return healthMapping.getResponse(
                status,
                LocalDateTime.now()
        );
    }

    @Override
    public HealthResponse checkHealthDocker() {

        boolean runningInDocker =
                new File("/.dockerenv").exists()
                        || System.getenv("KUBERNETES_SERVICE_HOST") != null;

        String status = runningInDocker
                ? "Application is running inside Docker"
                : "Application is not running inside Docker";

        return healthMapping.getResponse(
                status,
                LocalDateTime.now()
        );
    }
}