package com.interviewplatform.health.service;

import com.interviewplatform.health.dto.HealthResponse;

public interface HealthService {
   public HealthResponse checkHealthServer();
    public HealthResponse checkHealthDatabase();
    public HealthResponse checkHealthSystem();
    public HealthResponse checkHealthDocker();

}
