package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import com.example.bookstorespringboot.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserManager{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
