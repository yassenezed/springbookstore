package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.service.MyBookListManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyBookListController {
    @Autowired
    MyBookListManager myBookListManager;

    @RequestMapping("/deleteMyList/{id}")
    private String deleteFromMyList(@PathVariable("id") int id)
    {
        myBookListManager.deleteById(id);
        return "redirect:/my_books";

    }
}
