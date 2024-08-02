package ru.marina.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.User;
import ru.marina.shop.service.SecurityService;
import ru.marina.shop.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegController {
    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("/registration")
    public String getRegisterPage(Model model){
        model.addAttribute("user",new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(Model model ,@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("error_text", "Password is required");
            return "registration";
        }
        if (!confirmPassword.equals(user.getPassword())){
            model.addAttribute("error_text","Passwords do not match");
            return "registration";
        }
        if(!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$")){
            model.addAttribute("error_text", "Password must contains minimum 12 characters, at least one uppercase letter, one lowercase letter, one number and one special character");
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/catalog";
    }
}
