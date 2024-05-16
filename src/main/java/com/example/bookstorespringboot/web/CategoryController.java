package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Category;
import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.service.Author.AuthorManager;
import com.example.bookstorespringboot.service.Category.CategoryManager;
import com.example.bookstorespringboot.service.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class CategoryController {

    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private UserManager userManager;

    @GetMapping("/category_register")
    public String categoryRegister(Model model)
    {
        model.addAttribute("category",new Category());
        return "categoryTemplate/categoryRegister";
    }

    @PostMapping("/category_register")
    public String registerCategory(@ModelAttribute Category category, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        category.setUserCategory(user);
        categoryManager.addCategory(category);
        return "redirect:/available_categories";
    }
    @GetMapping("/available_categories")
    public String getAllCategories(Model model)
    {
        List<Category> listOfCategories = categoryManager.getAllCategories();
        model.addAttribute("categories" , listOfCategories);
        return "categoryTemplate/categoryList";
    }
    @RequestMapping("/editCategory/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Category myCategory = categoryManager.getCategoryById(id);
        model.addAttribute("category",myCategory);
        return "categoryTemplate/categoryEdit";
    }
    @RequestMapping("/deleteCategory/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        categoryManager.deleteById(id);
        return "redirect:/available_categories";
    }
}
