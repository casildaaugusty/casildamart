package com.casildamart.casildamart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/review")
    public String showReviewPage() {
        return "reviews";
    }

    @PostMapping("/review")
    public String submitReview(
            @RequestParam String username,
            @RequestParam Long productId,
            @RequestParam int rating,
            @RequestParam String comment) {

        Review review = new Review(
                username,
                productId,
                rating,
                comment
        );

        reviewRepository.save(review);

        return "redirect:/review?success=true";
    }
}