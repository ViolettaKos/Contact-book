package com.example.phonecontacts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true, nullable = false)
    private long id;

    private String name;
    // One contact may have multiple emails
    private String email;

    //One contact may have multiple phone numbers
    private String telephone;
}
