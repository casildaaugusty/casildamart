package com.casildamart.casildamart;

import org.springframework.stereotype.Controller;
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
    public String showOrderPage() {
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

        return "redirect:/order?success=true";
    }
}