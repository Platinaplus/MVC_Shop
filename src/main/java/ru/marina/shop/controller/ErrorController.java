package ru.marina.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }
}
