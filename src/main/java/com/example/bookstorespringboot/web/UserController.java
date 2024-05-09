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

    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "userTemplate/userLogin";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "userTemplate/userRegister"; // Return the registration form if there are validation errors
        }
        String EncodedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
        user.setPassword(EncodedPassword);
        userManager.addUser(user);
        return "redirect:login"; // Redirect to login page after successful registration
    }


    //Handler for Login Prl
    @PostMapping("/login")
    public String loginProcess (@RequestParam("username") String username,
                                @RequestParam("password") String password ) {
        User dbUser = userManager.findUserByUsername(username);
        Boolean isPasswordMatch=BCrypt.checkpw(password, dbUser.getPassword());
        if (isPasswordMatch)
            return "bookTemplate/home";
        else
            return "redirect:login"; // Redirect to login page after unsuccessful login
    }
}

