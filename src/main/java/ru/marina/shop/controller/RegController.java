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

import java.util.logging.Logger;
//@RequestParam("confirmPassword") String confirmPassword:
// This parameter is used to retrieve the confirmation password value from the query.

// The @RequestParam("confirmPassword") annotation specifies,
// that the value should be extracted from the request parameter named "confirmPassword" and converted to a string.

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
            return "registration"; // Title of the page with the registration form
        }
        if (!confirmPassword.equals(user.getPassword())){
            model.addAttribute("error_text","Passwords do not match");
            return "registration";
        }
        if (!userService.addUser(user)){
            model.addAttribute("error_text", "Such user already exist");
            return "registration";
        }
        securityService.autoLogin(user.getUsername(), user.getPassword());

        Logger.getLogger(RegController.class.getName()).info(String.format("Auto login %s", user.getUsername()));

        return "redirect:/lk"; // Redirects to the login page after successful registration
    }
}
