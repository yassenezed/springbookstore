package com.example.bookstorespringboot.service.User;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UserManager {

    public User addUser(User user);
    public User findUserByUsername(String username);
    public static List<User> users = new ArrayList<>();


}
