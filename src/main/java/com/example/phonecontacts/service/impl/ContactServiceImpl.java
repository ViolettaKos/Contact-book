package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.dto.ContactDTO;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.exception.UserNotFoundException;
import com.example.phonecontacts.model.Contact;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.repository.ContactRepository;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    @Override
    public String add(ContactDTO contactDTO, String token) throws ServiceException {
        User user=userRepository.findByToken(token);
        if (user!=null) {
            Contact contact=new Contact();
            contact.setName(contactDTO.getName())
                    .setEmails(contactDTO.getEmails())
                    .setPhones(contactDTO.getPhones())
                    .setUser(user);
            contactRepository.save(contact);
        } else {
            throw new UserNotFoundException();
        }
        return "Successfully added contact";
    }
}
