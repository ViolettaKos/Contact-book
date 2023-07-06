package com.example.phonecontacts.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private long id;

    @Column(name = "pass", nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String login;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contact> contactList;

    private String token;

    public User(String login, String password) {
        this.login=login;
        this.password=password;
    }
}
