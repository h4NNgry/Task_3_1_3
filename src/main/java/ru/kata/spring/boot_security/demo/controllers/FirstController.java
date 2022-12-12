package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class FirstController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("user")
    public String updateUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userInfo", user);
        return "user";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());

        return "admin";
    }
    @GetMapping("admin/{id}/delete")
    public String delete(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "delete";
    }

    @DeleteMapping("admin/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }
}
