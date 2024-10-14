package com.microservises.firstjobapp.review.services;

import com.microservises.firstjobapp.company.model.Company;
import com.microservises.firstjobapp.company.services.CompanyService;
import com.microservises.firstjobapp.review.model.Review;
import com.microservises.firstjobapp.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Review review, Long companyId) {
        Company company= companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId, Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Long companyId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId, Long companyId) {
        if (companyService.getCompanyById(companyId) != null
        && reviewRepository.existsById(reviewId)){
            Review review= reviewRepository.findById(reviewId).orElse(null);
            Company company= review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);

            return true;
        }
        return false;
    }


//    @Override
//    public void createReview(Review review) {
//        reviewRepository.save(review);
//    }
//
//    @Override
//    public List<Review> getAllReview() {
//        return reviewRepository.findAll();
//    }
//
//    @Override
//    public Review getReviewById(Long id) {
//        return reviewRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public Boolean deleteReviewById(Long id) {
//        if(reviewRepository.existsById(id)){
//            reviewRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Boolean updateReview(Review review, Long id) {
//        Optional<Review> reviewOptional= reviewRepository.findById(id);
//        if (reviewOptional.isPresent()){
//            Review reviewUpdate= reviewOptional.get();
//            reviewUpdate.setTitle(review.getTitle());
//            reviewUpdate.setDescription(reviewUpdate.getDescription());
//            reviewUpdate.setRating(reviewUpdate.getRating());
//            reviewUpdate.setCompany(review.getCompany());
//
//            reviewRepository.save(reviewUpdate);
//
//            return true;
//        }
//        return false;
//    }
}
