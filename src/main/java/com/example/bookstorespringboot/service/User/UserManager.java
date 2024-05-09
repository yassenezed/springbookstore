package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.User;

public interface UserManager {

    public User addUser(User user);
    public User findUserByUsername(String username);


}
