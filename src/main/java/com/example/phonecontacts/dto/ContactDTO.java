package com.example.phonecontacts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ContactDTO {

    @NotBlank(message = "Please enter name")
    private String name;

    @NotEmpty(message = "Please enter at least one email")
    @Email(message = "Please enter a valid email address")
    private Set<String> emails;

    @NotEmpty(message = "Please enter at least one phone number")
    @Pattern(regexp = "^\\+\\d{2}-\\d{3}-\\d{4}$", message = "Please enter a valid phone number")
    private Set<String> phones;


}
