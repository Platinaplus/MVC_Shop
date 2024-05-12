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
            return "registration";
        }
        if (!confirmPassword.equals(user.getPassword())){
            model.addAttribute("error_text","Passwords do not match");
            return "registration";
        }

        userService.addUser(user);

        //securityService.autoLogin(user.getUsername(), user.getPassword()); // TODO: need to fix this method

        return "redirect:/catalog";
    }
}
