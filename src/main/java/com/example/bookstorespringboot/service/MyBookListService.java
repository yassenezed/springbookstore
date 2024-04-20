package com.example.bookstorespringboot.service;


import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.MyBookList;
import com.example.bookstorespringboot.dao.repositories.MyBookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService implements MyBookListManager {

    @Autowired
    private MyBookListRepository myBookListRepository;
    @Override
    public MyBookList SaveMyBooks(MyBookList myBook) {
        return myBookListRepository.save(myBook);
    }

    public List<MyBookList> getAllMyBooks() {
        return myBookListRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        myBookListRepository.deleteById(id);
    }


}
