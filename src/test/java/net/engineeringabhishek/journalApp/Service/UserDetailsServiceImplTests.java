package net.engineeringabhishek.journalApp.Service;

import net.engineeringabhishek.journalApp.entity.User;
import net.engineeringabhishek.journalApp.repository.UserRepository;
import net.engineeringabhishek.journalApp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTests {

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("abhi").password("apple").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Abhishek");
        Assertions.assertNotNull(user);
    }

}
