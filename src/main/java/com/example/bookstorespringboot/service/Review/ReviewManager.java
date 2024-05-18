package com.example.bookstorespringboot.service.Review;

import com.example.bookstorespringboot.dao.entities.Author;
import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewManager {

    public Review addReview(Review review);
    public List<Review> getAllReviews();
    public Review getReviewById(int id);
    public void deleteById(int id);
    public Review updateReview(Review review);

}
