package com.example.phonecontacts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true, nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    // One contact may have multiple emails
    @ElementCollection
    @CollectionTable(name = "emails", joinColumns = @JoinColumn(name = "contact_id"))
    @Column(name = "email", nullable = false)
    private Set<String> emails;

    //One contact may have multiple phone numbers
    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "contact_id"))
    @Column(name = "phone", nullable = false)
    private Set<String> phones;

}
