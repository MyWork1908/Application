package org.seo.project.application.service.implement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.user.User;
import org.seo.project.application.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("huy");
        user.setPassword("password");
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());
    }

    @Test
    void loadUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        UserDetails userDetails = userService.loadUserById(user.getId());
        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());
    }
    @Test
    void loadUserById_exception() {
        Long id = user.getId();
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,()->{
            userService.loadUserById(id);
        });
    }
}