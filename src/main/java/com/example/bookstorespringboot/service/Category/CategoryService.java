package com.example.bookstorespringboot.service.Category;

import com.example.bookstorespringboot.dao.entities.Category;
import com.example.bookstorespringboot.dao.repositories.AuthorRepository;
import com.example.bookstorespringboot.dao.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CategoryService implements CategoryManager{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).get();

    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
