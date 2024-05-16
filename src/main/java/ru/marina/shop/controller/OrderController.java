package ru.marina.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.*;
import ru.marina.shop.entity.dto.OrderCreationDto;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.OrderService;
import ru.marina.shop.service.ProductService;
import ru.marina.shop.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(CartService cartService, ProductService productService, UserService userService, OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("")
    public String newOrder(Model model, @ModelAttribute OrderCreationDto order) {

        System.out.println(order.getCart());

        String day = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        Timestamp date = Timestamp.from(Instant.now());
        User user = userService.getCurrentUser();
        Order newOrder = new Order();
        newOrder.setOrderNumber(day+'-'+user.getUserId());
        newOrder.setPurchaseDate(date);
        newOrder.setUser_id(user);
        newOrder.setStatus(Status.CREATED);
        newOrder.setCarts(order.getCart());

        orderService.saveOrder(newOrder);

        order.getCart().forEach(cart -> {
            cartService.deleteFromCart(cart.getId());
        });

        model.addAttribute("orderNumber", day+'-'+user.getUserId());

        return "redirect:/order/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
}
