package org.example.uberauthservice.services;

import org.example.uberauthservice.dto.PassengerDto;
import org.example.uberauthservice.dto.PassengerSignupRequestDto;
import org.example.uberauthservice.repositories.PassengerRepository;
import com.example.uberentityservice.models.Passenger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder byCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passengerRepository = passengerRepository;
        this.byCryptPasswordEncoder = passwordEncoder;
    }

    public PassengerDto signup(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                .name(passengerSignupRequestDto.getName())
                .email(passengerSignupRequestDto.getEmail())
                .password(byCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword()))
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        return PassengerDto.from(newPassenger);
    }
}
