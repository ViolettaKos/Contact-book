package com.example.phonecontacts.service.impl;

import com.example.phonecontacts.service.TokenService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class TokenServiceImpl implements TokenService {
    protected static SecureRandom random = new SecureRandom();

    @Override
    public String randomToken(String login) {
        long longToken = Math.abs(random.nextLong());
        String random = Long.toString(longToken, 16);
        return (login + ":" + random);
    }
}
