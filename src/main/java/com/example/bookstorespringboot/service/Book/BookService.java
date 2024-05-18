package com.example.bookstorespringboot.service.Book;


import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service

public class BookService implements BookManager{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book, MultipartFile imageFile) {
        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            book.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookRepository.save(book);
    }


    @Override
    public List<Book> getAllBooks() {

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
