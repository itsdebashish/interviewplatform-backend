package com.interviewplatform.health.controller;

import com.interviewplatform.health.dto.HealthResponse;
import com.interviewplatform.health.mapping.HealthMapping;
import com.interviewplatform.health.service.HealthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@AllArgsConstructor
public class HealthController {

    private final HealthService healthService;
    private final HealthMapping healthMapping;

    @GetMapping("/")
    public ResponseEntity<HealthResponse> checkHealthServer() {
        HealthResponse response = healthService.checkHealthServer();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/database")
    public ResponseEntity<HealthResponse> checkHealthDatabase() {
        HealthResponse response = healthService.checkHealthDatabase();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/docker")
    public ResponseEntity<HealthResponse> checkHealthDocker() {
        HealthResponse response = healthService.checkHealthDocker();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/system")
    public ResponseEntity<HealthResponse> checkHealthSystem() {
        HealthResponse response = healthService.checkHealthSystem();
        return ResponseEntity.ok(response);
    }
}
