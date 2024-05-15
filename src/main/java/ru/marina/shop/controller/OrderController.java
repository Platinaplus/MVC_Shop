package ru.marina.shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Order;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.OrderService;
import ru.marina.shop.service.ProductService;
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
    public String newOrder(Model model, @ModelAttribute("order") Order order) {

        System.out.println(order);

//        String day = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
//        Timestamp date = Timestamp.from(Instant.now());
//        User user = userService.getCurrentUser();
//
//
//        order.setOrderNumber(day+'-'+user.getUserId());
//        order.setPurchaseDate(date);
//        orderService.saveOrder(order);
//
//
//        order.getCarts().forEach(cart -> {
//            cartService.deleteFromCart(cart.getId());
//        });
//
//
//        model.addAttribute("orderNumber", day+'-'+user.getUserId());

        return "redirect:/order/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
}
