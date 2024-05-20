package com.example.bookstorespringboot.dao.repositories;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //filter

    List<Book> findByNameContaining(String name);

    List<Book> findByDescriptionContaining(String description);

    List<Book> findByPriceBetween(float minPrice, float maxPrice);

    List<Book> findByAuthor(Author author);

    List<Book> findByCategories(Category category);

    @Query("SELECT b FROM Book b WHERE b.averageRating >= :rating")
    List<Book> findByAverageRatingGreaterThanEqual(@Param("rating") float rating);


    //filter



}
