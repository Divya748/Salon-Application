package com.eager2code.pojo;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalonDTO {

    private Long id;
    private String name;
    private List<String> images;
    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number")
    @NotBlank(message = "Email is mandatory")
    private String phoneNumber;

    @Email(message = "Enter valid email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String city;
    private Long ownerId;
    private LocalTime openTime;
    private LocalTime closeTime;
}
