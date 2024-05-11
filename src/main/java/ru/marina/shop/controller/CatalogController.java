package ru.marina.shop.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping({""})
    public String getRootCatalog(Model model) {
        model.addAttribute("category", "root");
        return "catalog";
    }

    @GetMapping("/brooches")
    public String getBrooches(Model model) {

        List<Product> products = productService.getProductsByCategory("brooches");

        model.addAttribute("category", "brooches");
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/tourniquets")
    public String getTourniquets(Model model) {

        List<Product> products = productService.getProductsByCategory("tourniquets");

        model.addAttribute("category", "tourniquets");
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {

        List<Product> products = productService.getAllProducts(Sort.by(Sort.Direction.DESC, "itemId"));

        model.addAttribute("products", products);
        model.addAttribute("category", "all");

        return "products";
    }

    @GetMapping("/id")
    public String getProduct(Model model) {

        List<Product> products = productService.getProductsByCategory("tourniquets");

        model.addAttribute("category", "tourniquets");
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/createProduct")
    public String newProduct(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(@Valid @ModelAttribute Product product, Model model, BindingResult result) {

        if (result.hasErrors()) {
            return "createProduct";
        }

        productService.createProduct(product);

        model.addAttribute("success", true);
        model.addAttribute("product", new Product());

        return "createProduct";
    }
}
