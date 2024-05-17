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
import ru.marina.shop.service.CartService;
import ru.marina.shop.service.FavoritesService;
import ru.marina.shop.service.ProductService;
import ru.marina.shop.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    public FavoritesController(ProductService productService, UserService userService, FavoritesService favoritesService, CartService cartService) {
        this.favoritesService = favoritesService;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("")
    public String favorites(Model model, @ModelAttribute("currentUser") User currentUser) {
        List<Product> favorites = favoritesService.getFavorites(currentUser);
        List<Product> cart = cartService.getCart(currentUser);

        model.addAttribute("favorites", favorites);
        model.addAttribute("cart", cart);

        return "favorites";
    }

    @GetMapping("/like")
    public String addToFavorites( @RequestParam Long productId, HttpServletRequest request, @ModelAttribute("currentUser") User currentUser ) {

        Product product = productService.getProductById(productId);

        Favorites favorite = new Favorites();
        favorite.setUser(currentUser);
        favorite.setProduct(product);

        favoritesService.addToFavorites(favorite);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/unlike")
    public String removeFromFavorites( @RequestParam Long productId, HttpServletRequest request, @ModelAttribute("currentUser") User currentUser ) {

        Product product = productService.getProductById(productId);
        favoritesService.removeFromFavorites(currentUser, product);

        return "redirect:" + request.getHeader("referer");
    }

    @ModelAttribute("currentUser")
    public User getUser() {
        return userService.getCurrentUser();
    }
}
