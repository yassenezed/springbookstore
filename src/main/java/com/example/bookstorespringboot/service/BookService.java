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

//we can use public void addbook and dont return nothing just b.save
    @Override
    public Book AddBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> gettAllBooks() {

        return bookRepository.findAll();
    }

    public Book getBookById(int id)
    {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {

        bookRepository.deleteById(id);

    }
}
