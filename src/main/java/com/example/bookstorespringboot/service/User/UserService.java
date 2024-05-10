package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import com.example.bookstorespringboot.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserManager{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Read");
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
