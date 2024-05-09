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

    //we can use public void addbook and dont return nothing just b.save
    @Override
    public Book AddBook(Book book) {
        return bookRepository.save(book);
    }

    public void  saveProductToDB(MultipartFile file, String name, String genre, String description
            , String price, Author author)
    {
        Book B = new Book();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            B.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }




        B.setName(name);
       // B.setGenre(genre);
        B.setAuthor(author);
        B.setDescription(description);
        B.setPrice(price);
        bookRepository.save(B);
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
