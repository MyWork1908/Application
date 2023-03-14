package org.seo.project.application.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.authentication.CustomUserDetails;
import org.seo.project.application.authentication.JwtTokenProvider;
import org.seo.project.application.models.user.LoginRequest;
import org.seo.project.application.models.user.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtTokenProvider tokenProvider;

    @InjectMocks
    UserController userController;

    @Test
    void authenticateUser() {
        LoginRequest loginRequest = new LoginRequest("huy", "123456");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        CustomUserDetails userDetails = new CustomUserDetails();
        when(authentication.getPrincipal()).thenReturn(userDetails);

        String jwtToken = "helloWorld";
        when(tokenProvider.generateToken(userDetails)).thenReturn(jwtToken);

        LoginResponse loginResponse = userController.authenticateUser(loginRequest);
        assertEquals(loginResponse.getAccessToken(),jwtToken);
    }
}