package org.example.uberauthservice.controller;

import org.example.uberauthservice.dto.PassengerSignupRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signupPassenger(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        return null;
    }
}
