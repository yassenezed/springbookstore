package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.service.User.UserManager;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserManager userManager;

    @GetMapping("/register")
    public String showRegistrationForm(User user) {
        return "userTemplate/userRegister";
    }


    @GetMapping("/login2")
    public String getLoginPage() {
        return "userTemplate/userLogin2";
    }

    @GetMapping("/registration2")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "userTemplate/userRegister2";
    }

    @PostMapping("/registration2")
    public String registerUser(@ModelAttribute User user) {
        userManager.addUser(user);
        return "redirect:/login2?success";
    }
}

