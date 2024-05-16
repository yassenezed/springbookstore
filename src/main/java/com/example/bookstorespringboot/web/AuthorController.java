package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.service.Author.AuthorManager;
import com.example.bookstorespringboot.service.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller

public class AuthorController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private AuthorManager authorManager;

    @GetMapping("/author_register")
    public String authorRegister(Model model)
    {
        model.addAttribute("author",new Author());
        return "authorTemplate/authorRegister";
    }

    @PostMapping("/author_register")
    public String RegisterAuthor(@ModelAttribute Author author, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        author.setUserAuthor(user);
        authorManager.addAuthor(author);
        return "redirect:/available_authors";
    }
    @GetMapping("/available_authors")
    public String getAllAuthors(Model model)
    {
        List<Author> listOfAuthors = authorManager.getAllAuthors();
        model.addAttribute("authors" , listOfAuthors);
        return "authorTemplate/authorList";
    }
    @RequestMapping("/editAuthor/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Author myAuthor = authorManager.getAuthorById(id);
        model.addAttribute("author",myAuthor);
        return "authorTemplate/authorEdit";
    }
    @RequestMapping("/deleteAuthor/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        authorManager.deleteById(id);
        return "redirect:/available_authors";
    }
}
