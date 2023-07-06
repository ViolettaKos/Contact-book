package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.ContactDTO;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.model.Contact;

import java.util.List;
import java.util.Set;

public interface ContactService {
    String add(ContactDTO contactDTO, String token) throws ServiceException;

    List<Contact> getAllContacts(String token);

    String edit(ContactDTO contactDTO, String token, String prevName) throws ServiceException;
    String delete(ContactDTO contactDTO, String token) throws ServiceException;

}
