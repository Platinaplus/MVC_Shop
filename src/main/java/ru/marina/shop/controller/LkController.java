package ru.marina.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import ru.marina.shop.entity.User;
import ru.marina.shop.repository.UserRepository;

@Controller
public class LkController {

    private final UserRepository userRepository;

    public LkController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/lk")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "lk"; // HTML page template name
    }
}
