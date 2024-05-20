package com.example.bookstorespringboot.service.Book;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Category;
import com.example.bookstorespringboot.dao.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface BookManager {

    public Book addBook(Book book,MultipartFile imageFile);
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    public void deleteById(int id);

    public double calculateAverageRating(Book book);
    public void calculateAndSetAverageRatings(List<Book> books);
    public Book updateBook(Book book);
    public void addBookToUserFavorites(Integer bookId, User user);
    public Collection<Book> getUserFavoriteBooks(User user);
    public void removeBookFromUserFavorites(Integer bookId, User user);

    //Filter
    public List<Book> filterBooksByName(String name);
    public List<Book> filterBooksByDescription(String description) ;
    public List<Book> filterBooksByPriceRange(float minPrice, float maxPrice) ;
    public List<Book> filterBooksByAuthor(Author author) ;
    public List<Book> filterBooksByCategory(Category category) ;
    public List<Book> filterBooksByRating(float rating) ;

    //Filter

    }
