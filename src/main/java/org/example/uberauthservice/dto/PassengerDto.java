package org.example.uberauthservice.dto;

import lombok.*;
import org.example.uberauthservice.models.Passenger;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;

    private String name;

    private String email;

    private String password; // encrypted password

    private String phoneNumber;

    private LocalDateTime createdAt;

    public static PassengerDto from(Passenger passenger) {
        PassengerDto result = PassengerDto.builder()
                .id(passenger.getId().toString())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .createdAt(passenger.getCreatedAt())
                .build();

        return result;
    }
}