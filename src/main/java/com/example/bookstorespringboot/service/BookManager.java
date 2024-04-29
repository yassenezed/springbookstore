package com.example.bookstorespringboot.service;

import com.example.bookstorespringboot.dao.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookManager {

    public Book AddBook(Book book);
    public List<Book> gettAllBooks();
    public Book getBookById(int id);

    public void deleteById(int id);
    public void  saveProductToDB(MultipartFile file, String name, String genre, String description
            , String price, String Author);


}
