package com.example.httploggerjava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class SignInController {

    private static final Logger log = LoggerFactory.getLogger(SignInController.class);
    private final Random random = new Random();

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signIn(@RequestHeader Map<String, String> headers) {
        log.info("========================================");
        log.info("Received /signin request with headers:");
        headers.forEach((key, value) -> log.info("{}: {}", key, value));
        log.info("========================================");
        // generate a random 10-digit number
        long randomValue = 1_000_000_000L + random.nextLong(9_000_000_000L);
        ResponseCookie cookie = ResponseCookie.from("session-cookie", String.valueOf(randomValue))
                .build();
        log.info("!!!!! Cookie created: {} !!!!!", cookie);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of(
                        "ok", true,
                        "txRefId", "",
                        "sessionId", ""
                ));
    }
}
