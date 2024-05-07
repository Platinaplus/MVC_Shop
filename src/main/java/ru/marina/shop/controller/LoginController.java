package ru.marina.shop.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.UserRepository;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("here");
        User userFromDB = userRepository.findByUsername(username);

        System.out.println("userFromDB: " + userFromDB.getEmail());

        if (password.equals(userFromDB.getPassword())) {
            model.addAttribute("username", username);
            return "redirect:/lk";
        }
        return "login";
    }
}
