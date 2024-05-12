package ru.marina.shop.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.Product;
import ru.marina.shop.entity.Role;
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

    @GetMapping("/product")
    public String getProduct(Model model, @RequestParam Long id) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "product-details";
    }

    @GetMapping("/createProduct")
    public String newProduct(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("mode", "create");
        model.addAttribute("success", "false");

        return "createProduct";
    }

    @GetMapping("/product/edit")
    public String formEditProduct(Model model, @RequestParam Long id) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("mode", "edit");
        model.addAttribute("success", "false");

        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(Model model, @ModelAttribute("product") @Valid Product product, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "createProduct";
        }

        productService.saveProduct(product);

        model.addAttribute("success", true);
        model.addAttribute("mode", "create");
        model.addAttribute("product", new Product());

        return "createProduct";
    }

    @PostMapping("/product/edit")
    public String editProduct(Model model, @RequestParam long id, @ModelAttribute("product") @Valid Product product, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "createProduct";
        }

        Product productFromDb = productService.getProductById(id);
        if (productFromDb == null) {
            return "redirect:/catalog";
        }

        productFromDb.setName(product.getName());
        productFromDb.setCategory(product.getCategory());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setImage(product.getImage());
        productFromDb.setLength(product.getLength());
        productFromDb.setWidth(product.getWidth());
        productFromDb.setMaterial(product.getMaterial());

        productService.saveProduct(productFromDb);
        model.addAttribute("success", true);
        model.addAttribute("mode", "edit");

        return "createProduct";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(Model model, @RequestParam Long id) {
        productService.deleteProductById(id);

        return "redirect:/catalog/all";
    }

    @ModelAttribute
    public Boolean isAdmin() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return login.equals(Role.ADMIN.name());
    }
}
