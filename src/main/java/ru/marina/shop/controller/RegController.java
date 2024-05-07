package ru.marina.shop.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marina.shop.entity.User;
import ru.marina.shop.service.UserService;

import java.util.logging.Logger;
//@RequestParam("confirmPassword") String confirmPassword:
// This parameter is used to retrieve the confirmation password value from the query.

// The @RequestParam("confirmPassword") annotation specifies,
// that the value should be extracted from the request parameter named "confirmPassword" and converted to a string.

@Controller
public class RegController {
    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String regUser(Model model){
        model.addAttribute("user",new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(Model model ,@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword) {

        if (bindingResult.hasErrors()) {
            // If there are validation errors, handle them here
            // For example, you can add them to the model and return a registration page with errors
            return "registration"; // Title of the page with the registration form
        }
        if (!confirmPassword.equals(user.getPassword())){
            model.addAttribute("error_text","Passwords do not match");
            return "registration";
        }

        userService.addUser(user);
        // User registration logic
        Logger.getLogger(RegController.class.getName()).info(user.toString());

        return "redirect:/lk"; // Redirects to the login page after successful registration
    }
}
