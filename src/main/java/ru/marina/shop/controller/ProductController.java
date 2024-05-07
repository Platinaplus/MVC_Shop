package ru.marina.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {
    @GetMapping("/product")
    public String product(){
        return "product";
    }
}
