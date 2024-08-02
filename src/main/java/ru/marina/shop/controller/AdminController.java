package ru.marina.shop.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Order;
import ru.marina.shop.entity.Role;
import ru.marina.shop.entity.Status;
import ru.marina.shop.entity.User;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.OrderService;
import ru.marina.shop.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/orders")
    public String admin(Model model){
        List<Order> orders = orderService.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("localDate", LocalDate.now());

        return "admin-orders";
    }

    @GetMapping("/order")
    public String order(Model model, @RequestParam Long orderId){
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("localDate", LocalDate.now());
        return "admin-order";
    }

    @PostMapping("/confirm")
    public String confirm(Model model, @ModelAttribute Order order){

        Order orderFromBd = orderService.findById(order.getId());
        orderFromBd.setStatus(Status.CONFIRMED);

        orderService.saveOrder(orderFromBd);

        List<Cart> userCart = cartService.getCartsByOrder(orderFromBd.getOrderNumber());

        userCart.forEach(cart -> {
            order.getCart().forEach(orderCart -> {
                if(cart.getProduct().getItemId() == (orderCart.getProduct().getItemId())){
                    cart.setQuantity(orderCart.getQuantity());
                    cartService.addToCard(cart);
                }
            });
        });
        return "redirect:/admin/orders";
    }

    @PostMapping("/done")
    public String done(Model model, @ModelAttribute Order order){

        Order orderFromBd = orderService.findById(order.getId());
        orderFromBd.setStatus(Status.DONE);

        orderService.saveOrder(orderFromBd);
        return "redirect:/admin/orders";
    }

    @GetMapping("/users")
    public String adminUsers(Model model){
        List<User> users = userService.allUsers();

        List<User> usersWithoutAdmin = users.stream().filter(user -> user.getRole().equals(Role.USER))
        .collect(Collectors.toList());

        model.addAttribute("users", usersWithoutAdmin);
        model.addAttribute("localDate", LocalDate.now());

        return "admin-users";
    }
    @GetMapping("/analytic")
    public ModelAndView getAnalytic(Map<String, Object> model){
        return new ModelAndView("admin-analytic");
    }
}
