package ru.marina.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String start(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!login.equals("anonymousUser")) {
            model.addAttribute("login", true);
        } else {
            model.addAttribute("login", false);
        }

        return "index";
    }
}
