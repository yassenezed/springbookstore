package com.example.bookstorespringboot.dao.repositories;

import com.example.bookstorespringboot.dao.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {


}
