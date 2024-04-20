package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.MyBookList;
import com.example.bookstorespringboot.service.BookManager;
import com.example.bookstorespringboot.service.MyBookListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookManager bookManager;

    @Autowired
    private MyBookListManager myBookListManager;
    //Redirect to home page Basic Controller
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String homeRegister()
    {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public String getAllBooks(Model model)
    {

//        @GetMapping("/available_books")
//        public ModelAndView getAllBook() {
//        List<Book>list=service.getAllBook();
//        return new ModelAndView("bookList","book", list);

        List<Book> listofbook = bookManager.gettAllBooks();
        model.addAttribute("books" , listofbook);
        return "bookList";
    }

    @PostMapping("save")
    public String addBook(@ModelAttribute Book b)
    {
        //We'ill try @Valid After and see the difference and also whats
        //the difference between RequestParam all of this works with name=
        //in form
        bookManager.AddBook(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList> listofmybooks = myBookListManager.getAllMyBooks();
        model.addAttribute("books",listofmybooks);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    // what is this RequestMapping ??
    //link are sensible to case?
    public String getMyList(@PathVariable("id") int id){
        Book b = bookManager.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListManager.SaveMyBooks(mb);
        return "redirect:/my_books";

    }





}
