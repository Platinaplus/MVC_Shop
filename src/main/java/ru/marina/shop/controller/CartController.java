package ru.marina.shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.CartRepository;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.ProductService;
import ru.marina.shop.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final CartRepository cartRepository;

    public CartController(CartService cartService, ProductService productService, UserService userService, CartRepository cartRepository) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @PostMapping("/add")
    public String addToCart( Model model, @RequestParam Long productId, HttpServletRequest request ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User  user = userService.findByName(username);
        Product product = productService.getProductById(productId);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(1);

        cartRepository.save(cart);

        model.addAttribute("cart", cart);

        return "redirect:" + request.getHeader("referer");
    }
}
