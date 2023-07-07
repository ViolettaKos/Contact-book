package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.dto.UserDTO;
import com.example.phonecontacts.exception.DuplicatedLoginException;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.exception.UserNotFoundException;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(UserDTO userDTO) throws ServiceException {
        if (userRepository.findByLogin(userDTO.getLogin()) == null) {
            log.info("No such user in DB");
            User user = new User(userDTO.getLogin(), passwordEncoder.encode(userDTO.getPass()));
            userRepository.save(user);
            return user.getLogin();
        } else {
            log.error("User already exists");
            throw new DuplicatedLoginException();
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            log.error("No user with such username");
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User update(UserDTO userDTO, String token) {
        User user = userRepository.findByLogin(userDTO.getLogin());
        user.setToken(token);
        userRepository.save(user);
        return user;
    }
}
