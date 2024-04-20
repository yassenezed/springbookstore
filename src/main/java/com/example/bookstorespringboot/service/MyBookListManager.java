package com.example.bookstorespringboot.service;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.MyBookList;

import java.util.List;

public interface MyBookListManager {

    public MyBookList SaveMyBooks(MyBookList myBook);
    public List<MyBookList> getAllMyBooks();
    public void deleteById (int id);
    }
