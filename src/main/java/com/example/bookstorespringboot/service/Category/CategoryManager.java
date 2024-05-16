package com.example.bookstorespringboot.service.Category;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Category;

import java.util.List;

public interface CategoryManager {

    public Category addCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int id);
    public void deleteById(int id);
}
