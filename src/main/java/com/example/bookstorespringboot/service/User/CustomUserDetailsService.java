package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.bookstorespringboot.dao.entities.User byUsername = userRepository.findByUsername(username);
        if(byUsername == null){return null;}
        return User.builder().username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .roles(byUsername.getRole())
                .build();
    }


}
