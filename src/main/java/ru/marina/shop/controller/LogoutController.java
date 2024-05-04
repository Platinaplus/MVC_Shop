package ru.marina.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Get the current session (if it exists) and remove user's attributes
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("username");
            session.invalidate(); // Finishing the session
        }

        // Redirect the user to the home page or another page of your choice
        return "redirect:/"; // URL name for redirection
    }
}
