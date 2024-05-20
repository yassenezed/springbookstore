package com.example.bookstorespringboot.web;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Review;
import com.example.bookstorespringboot.dao.entities.User;
import com.example.bookstorespringboot.service.Book.BookManager;
import com.example.bookstorespringboot.service.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserManager userManager;
    @Autowired
    private BookManager bookManager;
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
    @GetMapping("/availableBooks")
    public String showAllBooks(Model model){
        List<Book> listOfBooks = bookManager.getAllBooks();
        model.addAttribute("books", listOfBooks);
        return "bookTemplate/userBookList";
    }
    @PostMapping("/addFavorite")
    public String addToFavorite(@RequestParam("bookId") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        bookManager.addBookToUserFavorites(id, user);
        return "redirect:/userFavorites"; // Redirect to the favorites page
    }

    @GetMapping("/userFavorites")
    public String getUserFavorites(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        Collection<Book> favoriteBooks = bookManager.getUserFavoriteBooks(user);
        model.addAttribute("books", favoriteBooks);
        return "bookTemplate/userFavoriteBookList";
    }

    @PostMapping("/removeFavorite")
    public String removeFavorite(@RequestParam("bookId") Integer bookId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        bookManager.removeBookFromUserFavorites(bookId, user);
        return "redirect:/userFavorites"; // Redirect to the favorites page
    }

    @GetMapping("/show_all_users_admin")
    public String getAllReviews(Model model)
    {
        List<User> listOfUsers = userManager.getAllUsers();
        model.addAttribute("users" , listOfUsers);
        return "userTemplate/usersList";
    }

    @RequestMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userManager.getUserById(id);
        model.addAttribute("user", user);
        return "userTemplate/userEdit"; // Adjust the return value to your actual template path
    }
    @PostMapping("/editUser")
    public String updateReview(@RequestParam("id") Integer id,
                               @RequestParam("username") String username,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("birthDate") String birthDate,
                               @RequestParam("gender") String gender,
                               @RequestParam("address") String address,
                               @RequestParam("phoneNumber") String phoneNumber
                               )
    {
        User myUser = userManager.getUserById(id);
        myUser.setUsername(username);
        myUser.setFirstName(firstName);
        myUser.setLastName(lastName);
        myUser.setEmail(email);
        myUser.setBirthDate(birthDate);
        myUser.setGender(gender);
        myUser.setAddress(address);
        myUser.setPhoneNumber(phoneNumber);
        userManager.updateUser(myUser);
        return "redirect:/show_all_users_admin";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        userManager.deleteById(id);
        return "redirect:/available_reviews";
    }




}

