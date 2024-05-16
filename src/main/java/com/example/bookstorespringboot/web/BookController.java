package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.service.Book.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookManager bookManager;
    //Redirect to home page Basic Controller
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String homeRegister()
    {
        return "bookTemplate/bookRegister";
    }

    @GetMapping("/available_books")
    public String getAllBooks(Model model)
    {
//        @GetMapping("/available_books")
//        public ModelAndView getAllBook() {
//        List<Book>list=service.getAllBook();
//        return new ModelAndView("bookList","book", list);

        List<Book> listofbook = bookManager.getAllBooks();
        model.addAttribute("books" , listofbook);
        return "bookList";
    }
    @PostMapping("saveBackup")
    public String addBook(@ModelAttribute Book b)
    {
        //We'ill try @Valid After and see the difference and also whats
        //the difference between RequestParam all of this works with name=
        //in form
        bookManager.AddBook(b);
        return "redirect:/available_books";
    }
    @PostMapping("save")
    public String saveProduct(@RequestParam("image") MultipartFile file,
                              @RequestParam("name") String name,
                              @RequestParam("price") String price,
                              @RequestParam("description") String description,
                              @RequestParam("author") Author author,
                              @RequestParam("genre") String genre)
    {
        bookManager.saveProductToDB(file,name, genre, description,price,author);
        return "redirect:/available_books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book myBook = bookManager.getBookById(id);
        model.addAttribute("book",myBook);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        bookManager.deleteById(id);
        return "redirect:/available_books";
    }

}
