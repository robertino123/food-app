package com.bank.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @Size(min = 3, max = 30, message = "The first name should be between 3 and 30 characters.")
    @NotEmpty(message = "Please provide First Name.")
    private String firstName;

    @Size(min = 3, max = 30, message = "The last name should be between 3 and 30 characters.")
    @NotEmpty(message = "Please provide Last Name.")
    private String lastName;

    @NotNull(message = "Please provide age.")
    @Min(value = 1, message = "Please provide a valid age!")
    @Max(value = 150, message = "Please provide a valid age!")
    private int age;

    @NotNull(message = "Please provide phone number.")
    private Long phoneNumber;

    @Email(message = "Please provide valid email.")
    private String email;

}
