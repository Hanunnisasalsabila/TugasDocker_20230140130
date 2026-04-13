package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> userList = new ArrayList<>();

    private final String PASSWORD_NIM = "20230140130";

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if ("admin".equals(username) && PASSWORD_NIM.equals(password)) {
            session.setAttribute("isLoggedIn", true);
            return "redirect:/home";
        }
        model.addAttribute("error", "Username atau Password salah!");
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("isLoggedIn") == null) {
            return "redirect:/";
        }
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(HttpSession session, Model model) {
        if (session.getAttribute("isLoggedIn") == null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submit-form")
    public String submitForm(@ModelAttribute User user, HttpSession session) {
        if (session.getAttribute("isLoggedIn") == null) {
            return "redirect:/";
        }
        userList.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        userList.clear();
        return "redirect:/";
    }
}