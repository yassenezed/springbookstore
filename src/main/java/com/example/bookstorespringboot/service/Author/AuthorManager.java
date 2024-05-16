package com.example.bookstorespringboot.service.Author;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.User;

import java.util.List;

public interface AuthorManager {

    public Author addAuthor(Author author);
    public List<Author> getAllAuthors();
    public Author getAuthorById(int id);
    public void deleteById(int id);
}
