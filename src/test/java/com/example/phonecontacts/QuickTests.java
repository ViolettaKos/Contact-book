package com.example.phonecontacts;

import com.example.phonecontacts.controller.BasicActionsController;
import com.example.phonecontacts.dto.TokenDTO;
import com.example.phonecontacts.dto.UserDTO;
import com.example.phonecontacts.exception.DuplicatedLoginException;
import com.example.phonecontacts.exception.ServiceException;
import com.example.phonecontacts.model.User;
import com.example.phonecontacts.repository.UserRepository;
import com.example.phonecontacts.service.TokenService;
import com.example.phonecontacts.service.UserService;
import com.example.phonecontacts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuickTests {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;

    @Test
    public void testRegister() throws ServiceException {
        UserDTO userDTO = new UserDTO("testUser", "password");
        User user = new User("testUser", "encodedPass");

        when(userRepository.findByLogin(userDTO.getLogin())).thenReturn(null);
        when(passwordEncoder.encode(userDTO.getPass())).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenReturn(user);

        String res = userServiceImpl.register(userDTO);

        assertEquals(userDTO.getLogin(), res);
        verify(userRepository).findByLogin(userDTO.getLogin());
        verify(passwordEncoder).encode(userDTO.getPass());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterDuplicateUser() {
        UserDTO userDTO = new UserDTO("existingUser", "password");

        User existingUser = new User(userDTO.getLogin(), "encodedPassword");
        when(userRepository.findByLogin(userDTO.getLogin())).thenReturn(existingUser);

        assertThrows(DuplicatedLoginException.class, () -> userServiceImpl.register(userDTO));
    }

    @Test
    public void testFindByLogin() throws ServiceException {
        User existingUser = new User("existingUser", "password");

        when(userRepository.findByLogin("existingUser")).thenReturn(existingUser);

        User result = userServiceImpl.findByLogin("existingUser");

        assertEquals(existingUser, result);
        verify(userRepository).findByLogin("existingUser");
    }

    @Test
    public void testSuccessfulLogin() throws ServiceException {
        UserDTO userDTO = new UserDTO("username", "password");
        User user = new User("username", "encodedPassword");

        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        TokenService tokenService = Mockito.mock(TokenService.class);

        when(userService.findByLogin(userDTO.getLogin())).thenReturn(user);
        when(passwordEncoder.matches(userDTO.getPass(), user.getPassword())).thenReturn(true);
        when(tokenService.randomToken(user.getLogin())).thenReturn("token");

        BasicActionsController basicActionsController = new BasicActionsController(userService, null, passwordEncoder, tokenService);

        ResponseEntity<TokenDTO> response = basicActionsController.logIn(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());
    }


}
