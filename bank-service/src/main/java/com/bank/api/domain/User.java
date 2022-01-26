package com.bank.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
