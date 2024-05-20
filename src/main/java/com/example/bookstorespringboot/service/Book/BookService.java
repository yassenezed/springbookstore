package com.example.bookstorespringboot.service.Book;


import com.example.bookstorespringboot.dao.entities.*;
import com.example.bookstorespringboot.dao.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
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

    @Override
    // Calculate average rating for a book
    public double calculateAverageRating(Book book) {
        double totalRating = 0.0;
        int totalReviews = 0;
        if (book.getReviews() != null) {
            for (Review review : book.getReviews()) {
                totalRating += review.getRating();
                totalReviews++;
            }
        }
        return totalReviews > 0 ? totalRating / totalReviews : 0.0;
    }
    @Override
    public void calculateAndSetAverageRatings(List<Book> books) {
        for (Book book : books) {
            double averageRating = calculateAverageRating(book);
            book.setAverageRating(averageRating);
        }
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }


    public void addBookToUserFavorites(Integer bookId, User user) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        // Assuming we fetch the existing list of users, add the new user, and then set it back
        Collection<User> users = book.getUsers();
        users.add(user);
        book.setUsers(users);
        // Save the updated book
        bookRepository.save(book);
    }

    public Collection<Book> getUserFavoriteBooks(User user) {
        return user.getFavoriteBooks();
    }

    public void removeBookFromUserFavorites(Integer bookId, User user) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Collection<User> users = book.getUsers();
        users.remove(user);
        book.setUsers(users);
        bookRepository.save(book);
    }

    //Filter
    public List<Book> filterBooksByName(String name) {
        return bookRepository.findByNameContaining(name);
    }

    public List<Book> filterBooksByDescription(String description) {
        return bookRepository.findByDescriptionContaining(description);
    }

    public List<Book> filterBooksByPriceRange(float minPrice, float maxPrice) {
        return bookRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Book> filterBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> filterBooksByCategory(Category category) {
        return bookRepository.findByCategories(category);
    }

    public List<Book> filterBooksByRating(float rating) {
        return bookRepository.findByAverageRatingGreaterThanEqual(rating);
    }

    //filter


}
