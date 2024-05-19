package com.example.bookstorespringboot.service.Review;

import com.example.bookstorespringboot.dao.entities.Book;
import com.example.bookstorespringboot.dao.entities.Review;
import com.example.bookstorespringboot.dao.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewService implements ReviewManager{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(int id) {
        return reviewRepository.findById(id).get();
    }


    @Override
    public void deleteById(int id) {
        reviewRepository.deleteById(id);

    }

    public Review updateReview(Review review){
        return reviewRepository.save(review);
    }

}
