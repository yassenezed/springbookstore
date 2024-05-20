package com.example.bookstorespringboot;

import com.example.bookstorespringboot.dao.entities.*;
import com.example.bookstorespringboot.dao.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class BookStoreSpringBootApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public static void main(String[] args) {
        SpringApplication.run(BookStoreSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User(1,"","","","","","","","","",new Date(),"",null,null,null,null,null,new Date(),new Date());
        userRepository.save(user);
        Author author12 = new Author(1, "Alex", "Born In Canada", "", "Canadian", null,user,new Date(),new Date());
        authorRepository.save(author12);
        Category category = new Category();
        category.setCategoryName("Drama");
        categoryRepository.save(category);
        Book book = new Book(1, "Millionaire", List.of(category) ,List.of(user), "cc","",5 ,120,"cc", author12,user,null,new Date(),new Date());
        bookRepository.save(book);
        book.setUserBook(user);
        Review review = new Review(1,5,"cc",new Date(),user,book,new Date(),new Date());
        reviewRepository.save(review);
    }
}
