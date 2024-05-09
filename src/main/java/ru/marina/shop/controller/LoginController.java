package ru.marina.shop.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.marina.shop.entity.User;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out");
        }
        model.addAttribute("user", new User());
        return "login";
    }
}
