package com.casildamart.casildamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Show review page
    @GetMapping("/reviews")
    public String showReviewPage(
            @RequestParam("productId") Long productId,
            Model model) {

        model.addAttribute("productId", productId);

        model.addAttribute(
                "reviews",
                reviewRepository.findByProductId(productId)
        );

        return "reviews";
    }

    // Save review
    @PostMapping("/submit-review")
    public String submitReview(
            @RequestParam("productId") Long productId,
            @RequestParam("rating") int rating,
            @RequestParam("comment") String comment) {

        Review review = new Review();

        review.setProductId(productId);
        review.setRating(rating);
        review.setComment(comment);

        reviewRepository.save(review);

        return "redirect:/reviews?productId=" + productId;
    }
}