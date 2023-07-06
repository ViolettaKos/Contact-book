package com.example.phonecontacts.service;

import com.example.phonecontacts.dto.UserDTO;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.model.User;

public interface UserService {
    String register(UserDTO userDTO) throws ServiceException;
    User findByLogin(String login) throws ServiceException;

    User update(UserDTO userDTO, String token);
}
