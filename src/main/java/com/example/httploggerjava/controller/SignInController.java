package com.example.httploggerjava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class SignInController {

    private static final Logger log = LoggerFactory.getLogger(SignInController.class);
    private final Random random = new Random();

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestHeader Map<String, String> headers) {
        log.info("Received /signin request with headers:");
        headers.forEach((key, value) -> log.info("{}: {}", key, value));

        // generate a random 10-digit number
        long randomValue = 1_000_000_000L + random.nextLong(9_000_000_000L);

        return ResponseEntity
                .ok()
                .header("Cookie", String.valueOf(randomValue))
                .body("Signed in successfully!");
    }

    // Optional: also allow GET for quick browser testing
    @GetMapping("/signin")
    public ResponseEntity<String> signInGet(@RequestHeader Map<String, String> headers) {
        return signIn(headers);
    }
}
