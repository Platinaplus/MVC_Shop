package ru.marina.shop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.Cart;
import ru.marina.shop.entity.Favorites;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.CartRepository;
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.FavoritesService;
import ru.marina.shop.service.ProductService;
import ru.marina.shop.service.UserService;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;
    private final ProductService productService;
    private final UserService userService;

    public FavoritesController(ProductService productService, UserService userService, FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/like")
    public String addToFavorites( @RequestParam Long productId, HttpServletRequest request ) {
        User user = userService.getCurrentUser();
        Product product = productService.getProductById(productId);

        Favorites favorite = new Favorites();
        favorite.setUser(user);
        favorite.setProduct(product);

        favoritesService.addToFavorites(favorite);

        return "redirect:" + request.getHeader("referer");
    }
}
