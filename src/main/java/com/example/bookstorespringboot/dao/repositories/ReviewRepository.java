package com.example.bookstorespringboot.dao.repositories;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
