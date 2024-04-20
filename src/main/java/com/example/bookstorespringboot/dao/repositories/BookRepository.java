package com.example.bookstorespringboot.dao.repositories;

import com.example.bookstorespringboot.dao.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
