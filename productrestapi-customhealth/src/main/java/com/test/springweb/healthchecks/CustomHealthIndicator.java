package com.test.springweb.healthchecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    /*@Override
    public Health health() {
        boolean error = false;
        if(error){
            return Health.down().withDetail("Error Key",123).build();
        }
        return Health.up().build();
    }*/

    //Developed the below one for meaningful understanding
    private RestTemplate restTemplate;
    private final String externalServiceUrl = "http://localhost:8080/productapi/productsinfo";

    public CustomHealthIndicator() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Health health() {
        try {
            // Perform the custom health check logic
           String status = restTemplate.getForObject(externalServiceUrl,String.class);
            if ("OK".equals(status)) {
                return Health.up()
                        .withDetail("service", "External API")
                        .withDetail("status", status)
                        .build();
            } else {
                return Health.down()
                        .withDetail("service", "External API")
                        .withDetail("error", "API returned non-OK status: " + status)
                        .build();
            }
        } catch (Exception e) {
            // Handle exceptions that occur during the check
            return Health.down()
                    .withDetail("service", "External API")
                    .withDetail("error", "Connection failed: " + e.getMessage())
                    .build();
        }
    }
}
