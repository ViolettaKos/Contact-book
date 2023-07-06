package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.ContactDTO;

public interface ContactService {
    String add(ContactDTO contactDTO, String token);
}
