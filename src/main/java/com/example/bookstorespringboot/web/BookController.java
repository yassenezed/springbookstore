package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Category;
import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.service.Author.AuthorManager;
import com.example.bookstorespringboot.service.Book.BookManager;
import com.example.bookstorespringboot.service.Category.CategoryManager;
import com.example.bookstorespringboot.service.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookManager bookManager;
    @Autowired
    private AuthorManager authorManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private UserManager userManager;
    //Redirect to home page Basic Controller
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/available_books")
    public String getAllBooks(Model model)
    {
        List<Book> listOfBook = bookManager.getAllBooks();
        model.addAttribute("books" , listOfBook);
        return "bookTemplate/bookList";
    }
    @GetMapping("/book_register")
    public String homeRegister(Model model)
    {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorManager.getAllAuthors());
        model.addAttribute("categories", categoryManager.getAllCategories());
        return "bookTemplate/bookRegister";
    }



    @PostMapping("book_register")
    public String addBook(@ModelAttribute Book b,
                          @RequestParam("author") int authorId,
                          @RequestParam("category") List<Integer> categoryIds,
                          @RequestParam("file") MultipartFile imageFile,
                          @AuthenticationPrincipal UserDetails userDetails
                          )
    {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        b.setUserBook(user);
        // Retrieve author object based on authorId
        Author author = authorManager.getAuthorById(authorId);
        // Retrieve category objects based on categoryIds
        List<Category> categories = new ArrayList<>();
        for (int categoryId : categoryIds) {
            Category category = categoryManager.getCategoryById(categoryId);
            categories.add(category);
        }
        // Set author and categories to the book object
        b.setAuthor(author);
        b.setCategories(categories);


        // Save the book object
        bookManager.addBook(b,imageFile);
        return "redirect:/available_books";
    }


    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book myBook = bookManager.getBookById(id);
        if (myBook == null) {
            return "redirect:/error";
        }
        model.addAttribute("book",myBook);
        return "bookEdit";
    }
    @PostMapping("/editBook")
    public String updateProduct(@RequestParam("id") Integer id,
                                @RequestParam("name") String description,
                                @RequestParam("price") double price) {
        Book myBook = bookManager.getBookById(id);
        myBook.setDescription(description);
        //myBook.setPrice();
        //myBook.setName();
        //ookManager.updateProduct(product);
        return "redirect:/getProductsList";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        bookManager.deleteById(id);
        return "redirect:/available_books";
    }

}
