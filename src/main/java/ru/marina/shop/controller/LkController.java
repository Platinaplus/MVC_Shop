package ru.marina.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;


import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class LkController {

    @GetMapping("/lk")
    @PreAuthorize("isAuthenticated()")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Checking if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // If the user is authenticated, get his name
            String username = authentication.getName();
            // Passing the user name to the model
            model.addAttribute("username", username);
        }

        return "lk"; // HTML page template name
    }
}
