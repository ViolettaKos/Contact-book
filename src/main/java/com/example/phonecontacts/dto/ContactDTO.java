package com.example.phonecontacts.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ContactDTO {

    @NotBlank(message = "Please enter name")
    private String name;


}
