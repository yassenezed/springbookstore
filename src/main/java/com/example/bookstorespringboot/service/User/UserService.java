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
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserManager{

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    /*@PostConstruct
    @Bean
    public void postConstruct() {
        User user = new User();
        user.setRole("Admin");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);
    }*/

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Read");
        user.setRegistrationDate(new Date());
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id){
        return userRepository.findById(id).get();
    }


    @Override
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

}
