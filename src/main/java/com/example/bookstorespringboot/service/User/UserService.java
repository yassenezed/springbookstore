package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import com.example.bookstorespringboot.dao.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService implements UserManager{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @PostConstruct
    @Bean
    public void postConstruct() {
        User user = new User();
        user.setRole("Admin");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Read");
        user.setRegistrationDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
