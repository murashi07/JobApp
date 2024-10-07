package com.microservises.firstjobapp.review.services;


import com.microservises.firstjobapp.review.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean createReview(Review review, Long companyId);

    Review getReviewById(Long reviewId, Long companyId);

    boolean updateReview(Long reviewId, Long companyId, Review review);

    boolean deleteReview(Long reviewId, Long companyId);

//    void createReview(Review review);
//
//    List<Review> getAllReview();
//
//    Review getReviewById(Long id);
//
//    Boolean deleteReviewById(Long id);
//
//    Boolean updateReview(Review review, Long id);
}
