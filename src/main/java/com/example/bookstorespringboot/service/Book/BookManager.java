package com.example.bookstorespringboot.service.Book;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookManager {

    public Book AddBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(int id);

    public void deleteById(int id);
    public void  saveProductToDB(MultipartFile file,String name, String genre, String description
            , String price, Author author);


}
