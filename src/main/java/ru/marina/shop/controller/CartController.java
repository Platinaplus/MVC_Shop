package ru.marina.shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.entity.dto.OrderCreationDto;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.ProductService;
import ru.marina.shop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showCart(Model model, @ModelAttribute("currentUser") User currentUser) {
        OrderCreationDto order = new OrderCreationDto();
        List<Cart> carts = cartService.getCarts(currentUser);
        carts.forEach(order::addCart);

        model.addAttribute("order", order);

        return "cart";
    }

    @GetMapping("/add")
    public String addToCart( @RequestParam Long productId, HttpServletRequest request, @ModelAttribute("currentUser") User currentUser ) {
        Product product = productService.getProductById(productId);

        Cart cart = new Cart();
        cart.setUser(currentUser);
        cart.setProduct(product);
        cart.setQuantity(1);

        cartService.addToCard(cart);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/edit")
    public String editCart( @RequestParam Product product, @RequestParam Integer quantity, @ModelAttribute("currentUser") User currentUser ) {

        cartService.editCart(currentUser, product, quantity);

        return "cart";
    }

    @GetMapping("/delete")
    public String deleteProduct( @RequestParam Long id, HttpServletRequest request) {

        cartService.deleteFromCart(id);

        return "redirect:" + request.getHeader("referer");
    }

    @ModelAttribute("currentUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
