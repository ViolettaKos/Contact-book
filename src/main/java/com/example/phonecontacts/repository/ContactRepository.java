package com.example.phonecontacts.repository;

import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByUserAndAndName(User user, String name);
}
