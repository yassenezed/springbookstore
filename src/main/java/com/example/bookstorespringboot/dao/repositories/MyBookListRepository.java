package com.example.bookstorespringboot.dao.repositories;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookListRepository extends JpaRepository<MyBookList,Integer> {

}
