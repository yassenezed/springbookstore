package com.example.bookstorespringboot.service.Book;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookManager {

    public Book addBook(Book book,MultipartFile imageFile);
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    public void deleteById(int id);

    public double calculateAverageRating(Book book);
    public void calculateAndSetAverageRatings(List<Book> books);
    public Book updateBook(Book book);

    }
