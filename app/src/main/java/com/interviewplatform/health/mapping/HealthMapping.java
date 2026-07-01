package com.interviewplatform.health.mapping;

import com.interviewplatform.health.dto.HealthResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HealthMapping {

    public HealthResponse getResponse(String message, LocalDateTime dateTime) {
         return HealthResponse.builder().message(message).timestamp(dateTime).build();
    }
}
