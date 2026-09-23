package com.casildamart.casildamart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order")
    public String showOrderPage(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double total,
            @RequestParam(required = false) String success,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("productId", productId);
        model.addAttribute("productName", productName);
        model.addAttribute("quantity", quantity);
        model.addAttribute("total", total);

        if ("true".equals(success)) {
            model.addAttribute(
                    "successMessage",
                    "Order placed successfully!"
            );
        }

        return "order";
    }

    @PostMapping("/order")
    public String placeOrder(
            @RequestParam String username,
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam double total) {

        Order order = new Order(
                username,
                productId,
                quantity,
                total,
                "PLACED"
        );

        orderRepository.save(order);

        return "redirect:/order?success=true&username=" + username;
    }
}