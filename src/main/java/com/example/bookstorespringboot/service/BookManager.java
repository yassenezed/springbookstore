package com.example.bookstorespringboot.service;

import com.example.bookstorespringboot.dao.entities.Book;

import java.util.List;

public interface BookManager {

    public Book AddBook(Book book);
    public List<Book> gettAllBooks();

}
