package com.example.bookstorespringboot.web;


import com.example.bookstorespringboot.dao.entities.*;
import com.example.bookstorespringboot.service.Book.BookManager;
import com.example.bookstorespringboot.service.Review.ReviewManager;
import com.example.bookstorespringboot.service.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewManager reviewManager;
    @Autowired
    private BookManager bookManager;
    @Autowired
    private UserManager userManager;


    @GetMapping("/review_register")
    public String reviewRegister(Model model, @RequestParam(name = "id", required = false) Integer bookId)
    {
        model.addAttribute("review", new Review());
        model.addAttribute("books", bookManager.getAllBooks());
        model.addAttribute("selectedBookId", bookId); // Pass the book ID as a model attribute

        return "reviewTemplate/reviewRegister";
    }

    @PostMapping("review_register")
    public String addBook(@ModelAttribute Review review,
                          @RequestParam("book") int bookId,
                          @AuthenticationPrincipal UserDetails userDetails
    )
    {
        String username = userDetails.getUsername();
        User user = userManager.findUserByUsername(username);
        review.setUserReview(user);
        // Retrieve book object based on authorId
        Book book = bookManager.getBookById(bookId);
        // Retrieve category objects based on categoryIds
        review.setBookReview(book);
        review.setTimestamp(new Date());
        // Save the review object
        reviewManager.addReview(review);
        return "redirect:/available_reviews";
    }

    @GetMapping("/available_reviews")
    public String getAllReviews(Model model)
    {
        List<Review> listOfReviews = reviewManager.getAllReviews();
        model.addAttribute("reviews" , listOfReviews);
        return "reviewTemplate/reviewList";
    }
    @RequestMapping("/editReview/{id}")
    public String editReview(@PathVariable("id") int id, Model model) {
        Review review = reviewManager.getReviewById(id);
        List<Book> listOfBooks = bookManager.getAllBooks(); // Assuming you have a method to get all books
        model.addAttribute("review", review);
        model.addAttribute("books", listOfBooks);
        return "reviewTemplate/reviewEdit"; // Adjust the return value to your actual template path
    }
    @PostMapping("/editReview")
    public String updateReview(@RequestParam("id") Integer id,
                               @RequestParam("comment") String comment,
                               @RequestParam("rating") int rating,
                               @RequestParam("bookReview.id") int bookId)
    {
        Review myReview = reviewManager.getReviewById(id);
        Book myBook = bookManager.getBookById(bookId);
        myReview.setComment(comment);
        myReview.setRating(rating);
        myReview.setBookReview(myBook);
        reviewManager.updateReview(myReview);
        return "redirect:/available_reviews";
    }

    @RequestMapping("/deleteReview/{id}")
    public String deleteById(@PathVariable("id") int id)
    {
        reviewManager.deleteById(id);
        return "redirect:/available_reviews";
    }
}
