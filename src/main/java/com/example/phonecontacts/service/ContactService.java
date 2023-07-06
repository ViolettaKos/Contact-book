package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.ContactDTO;
import com.example.phonecontacts.exception.ServiceException;

public interface ContactService {
    String add(ContactDTO contactDTO, String token) throws ServiceException;
}
