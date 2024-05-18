package com.example.bookstorespringboot.service.Author;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.dao.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService implements AuthorManager{

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(int id)
    {
        return authorRepository.findById(id).get();
    }


    @Override
    public void deleteById(int id) {

        authorRepository.deleteById(id);

    }

    public Author updateAuthor(Author author) {
            return authorRepository.save(author);

    }
}
