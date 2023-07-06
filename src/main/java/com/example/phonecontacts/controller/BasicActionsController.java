package com.example.phonecontacts.controller;

import com.example.phonecontacts.dto.ContactDTO;
import com.example.phonecontacts.dto.TokenDTO;
import com.example.phonecontacts.dto.UserDTO;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.service.ContactService;
import com.example.phonecontacts.service.TokenService;
import com.example.phonecontacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/basic")
@RestController
public class BasicActionsController {

    private UserService userService;
    private ContactService contatService;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> registration(@RequestBody UserDTO userDTO) throws ServiceException {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> logIn(@RequestBody UserDTO userDTO) {
        try {
            User user=userService.findByLogin(userDTO.getLogin());
            if(passwordEncoder.matches(userDTO.getPass(), user.getPassword())) {
                String token=tokenService.randomToken(user.getLogin());
                user.setToken(token);
                userService.update(userDTO, token);
                return ResponseEntity.ok(new TokenDTO(token));
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (ServiceException e) {
            log.error("No such user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addContact(@RequestParam String token,
                                       @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contatService.add(contactDTO, token));
    }
}
