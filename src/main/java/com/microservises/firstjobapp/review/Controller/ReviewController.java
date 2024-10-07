package com.microservises.firstjobapp.review.Controller;

import com.microservises.firstjobapp.review.model.Review;
import com.microservises.firstjobapp.review.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId ),HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@RequestBody Review review,
                                               @PathVariable Long companyId){
        boolean isReviewSaved = reviewService.createReview(review, companyId);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not  created successfully", HttpStatus.NOT_FOUND);
    }

        @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId,
                                                @PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId, companyId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @PathVariable Long companyId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId,companyId, review);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);

        else
            return new ResponseEntity<>("Review  not updated",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId,
                                               @PathVariable Long companyId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId, companyId);

        if (isReviewDeleted)
            return new ResponseEntity<>("review deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("review not deleted", HttpStatus.NOT_FOUND);

    }

//    @PostMapping("/companies/reviews")
//    public ResponseEntity<String> createReview(@RequestBody Review review){
//        reviewService.createReview(review);
//        return new ResponseEntity<>("Review created successfully", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/companies/reviews")
//    public ResponseEntity<List<Review>> getAllReview(){
//        return new ResponseEntity<>(reviewService.getAllReview(),HttpStatus.OK);
//    }
//
//    @GetMapping("/companies/reviews/{id}")
//    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
//        Review review = reviewService.getReviewById(id);
//        if (review != null){
//            return ResponseEntity.ok(review);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/companies/reviews/{id}")
//    public ResponseEntity<String> deleteReview(@PathVariable Long id){
//        boolean isDeleted= reviewService.deleteReviewById(id);
//        if (isDeleted) {
//            return ResponseEntity.ok("Review Deleted Successfully");
//        }
//        return new ResponseEntity<>("Not Deleted Successfully", HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("/companies/reviews/{id}")
//    public ResponseEntity<String> updateReview(@RequestBody Review review,
//                                               @PathVariable Long id){
//        reviewService.updateReview(review, id);
//        return ResponseEntity.ok("review updated successfully");
//    }
}
