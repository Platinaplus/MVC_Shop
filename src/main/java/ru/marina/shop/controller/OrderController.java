package ru.marina.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.*;
import ru.marina.shop.entity.dto.OrderCreationDto;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.OrderService;
import ru.marina.shop.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(CartService cartService, UserService userService, OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("")
    public String newOrder(Model model, @ModelAttribute OrderCreationDto order) {

        String day = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        Timestamp date = Timestamp.from(Instant.now());
        User user = userService.getCurrentUser();
        Order newOrder = new Order();
        newOrder.setOrderNumber(day + '-' + user.getUserId());
        newOrder.setPurchaseDate(date);
        newOrder.setUserId(user);
        newOrder.setStatus(Status.CREATED);
        newOrder.setCart(order.getCart());

        orderService.saveOrder(newOrder);

        List<Cart> userCart = cartService.getCarts(user);
        userCart.forEach(cart -> {
            order.getCart().forEach(orderCart -> {
                if (cart.getId().equals(orderCart.getId())) {
                    cart.setOrdered(true);
                    cart.setQuantity(orderCart.getQuantity());
                    cartService.addToCard(cart);
                }
            });
        });

        return "redirect:/order/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
}
