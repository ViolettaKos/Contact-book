package com.example.phonecontacts.controller;

import com.example.phonecontacts.dto.UserDTO;
import com.example.phonecontacts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/basic")
@RestController
public class BasicActionsController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.register(userDTO));
    }
}
