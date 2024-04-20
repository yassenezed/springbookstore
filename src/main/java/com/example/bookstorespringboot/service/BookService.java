package com.example.bookstorespringboot.service;


import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookService implements BookManager{

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book AddBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> gettAllBooks() {

        return bookRepository.findAll();
    }
}
