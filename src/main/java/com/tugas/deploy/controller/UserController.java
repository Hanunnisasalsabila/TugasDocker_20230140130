package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import com.tugas.deploy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140130";

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)){
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

    }

    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("dataList", userService.getAllUser());
        return "home";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/home";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam String id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam String id, @ModelAttribute User user){
        userService.updateUser(id, user);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
        return "redirect:/home";
    }
}