package ru.marina.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.marina.shop.entity.Product;
import ru.marina.shop.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getRootCatalog(Model model) {
        return "catalog";
    }

    @GetMapping("/brooches")
    public String getBrooches(Model model) {

        model.addAttribute("category", "brooches");
        return "products";
    }

    @GetMapping("/tourniquets")
    public String getTourniquets(Model model) {

        model.addAttribute("category", "tourniquets");

        return "products";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {

        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        model.addAttribute("category", "all");

        return "products";
    }
}
