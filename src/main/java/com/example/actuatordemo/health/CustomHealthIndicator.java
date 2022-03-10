package com.example.actuatordemo.health;

import java.security.SecureRandom;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Use the builder to build the health status details that should be reported.
        // If you throw an exception, the status will be DOWN with the exception message.
        int rand = getRandomNumberInRange(0, 100);
        if (rand < 50) {
            builder.up()
                    .withDetail("app", "Alive and Kicking")
                    .withDetail("error", "Nothing! I'm good.");
        } else {
            builder.down()
                    .withDetail("app", "I'm Die")
                    .withDetail("error", "Sorry, I'm hungry.");
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        SecureRandom r = new SecureRandom();
        return r.nextInt((max - min) + 1) + min;
    }
}
