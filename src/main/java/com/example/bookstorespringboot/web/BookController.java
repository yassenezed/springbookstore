package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.*;
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

    @GetMapping("/available_books_admin")
    public String getAllBooks(Model model) {
        List<Book> listOfBooks = bookManager.getAllBooks();
        bookManager.calculateAndSetAverageRatings(listOfBooks);
        // Calculate average ratings for all books
        model.addAttribute("books", listOfBooks);
        return "bookTemplate/bookListAdmin";
    }
    //filter
    @GetMapping("/available_books")
    public String getAllBooks(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "minPrice", required = false) Float minPrice,
                              @RequestParam(value = "maxPrice", required = false) Float maxPrice,
                              @RequestParam(value = "authorId", required = false) Integer authorId,
                              @RequestParam(value = "categoryId", required = false) Integer categoryId,
                              @RequestParam(value = "rating", required = false) Float rating,
                              Model model) {
        List<Book> filteredBooks = new ArrayList<>();
        // Filtering logic
        if (name != null && !name.isEmpty()) {
            filteredBooks = bookManager.filterBooksByName(name);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else if (description != null && !description.isEmpty()) {
            filteredBooks = bookManager.filterBooksByDescription(description);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else if (minPrice != null && maxPrice != null) {
            filteredBooks = bookManager.filterBooksByPriceRange(minPrice, maxPrice);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else if (authorId != null) {
            Author author = authorManager.getAuthorById(authorId);
            filteredBooks = bookManager.filterBooksByAuthor(author);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else if (categoryId != null) {
            Category category = categoryManager.getCategoryById(categoryId);
            filteredBooks = bookManager.filterBooksByCategory(category);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else if (rating != null) {
            filteredBooks = bookManager.filterBooksByRating(rating);
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        } else {
            filteredBooks = bookManager.getAllBooks(); // Fallback to all books if no filter is applied
            bookManager.calculateAndSetAverageRatings(filteredBooks);

        }

        model.addAttribute("books", filteredBooks);
        model.addAttribute("authors", authorManager.getAllAuthors());
        model.addAttribute("categories", categoryManager.getAllCategories());
        return "bookTemplate/bookList";
    }
    //filter

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
        List<Author> listOfAuthors = authorManager.getAllAuthors(); // Assuming you have a method to get all books
        List<Category> listOfCategories = categoryManager.getAllCategories(); // Assuming you have a method to get all books
        model.addAttribute("book",myBook);
        model.addAttribute("authors", listOfAuthors);
        model.addAttribute("categories", listOfCategories);
        return "bookTemplate/bookEdit";
    }


    @PostMapping("/editBook")
    public String updateBook(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("amazonLink") String amazonLink,
                               @RequestParam("price") float price,
                               @RequestParam("author.id") int authorId,
                               @RequestParam("category.id") List<Integer> categoryIds,
                               @RequestParam("file") MultipartFile file
                               )
    {
        Book myBook = bookManager.getBookById(id);
        myBook.setName(name);
        myBook.setDescription(description);
        myBook.setPrice(price);
        myBook.setAmazonLink(amazonLink);

        Author author = authorManager.getAuthorById(authorId);
        myBook.setAuthor(author);


        // Retrieve category objects based on categoryIds
        List<Category> categories = new ArrayList<>();
        for (int categoryId : categoryIds) {
            Category category = categoryManager.getCategoryById(categoryId);
            categories.add(category);
        }

        myBook.setCategories(categories);


        if (!file.isEmpty()) {
            try {
                byte[] imageBytes = file.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                myBook.setImage(base64Image);
            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace();
            }
        }
        bookManager.updateBook(myBook);
        return "redirect:/available_books";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        bookManager.deleteById(id);
        return "redirect:/available_books";
    }



    //book detail
    @GetMapping("/bookDetail/{id}")
    public String showBookDetail(@PathVariable("id") Integer id, Model model) {
        // Retrieve the book details from the service layer based on the provided id
        Book book = bookManager.getBookById(id);
        // Add the book object to the model so it can be accessed in the view
        model.addAttribute("book", book);

        // Return the name of the Thymeleaf template for rendering the book detail page
        return "bookTemplate/bookDetail";
    }

}
