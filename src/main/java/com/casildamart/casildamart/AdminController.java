package com.casildamart.casildamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    public AdminController(OrderRepository orderRepository,
                           ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {

        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("reviews", reviewRepository.findAll());

        return "admin";
    }
}
