package com.course.autopodborplatform.controllers;

import com.course.autopodborplatform.service.UserRepr;
import com.course.autopodborplatform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRepr());
        return "register";
    }
    @PostMapping("/register")
    public String registerNewUser(@Valid UserRepr userRepr, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!userRepr.getPassword().equals(userRepr.getRepeatPassword())){
            bindingResult.rejectValue("password","","Пароли не совпадают");
            return "register";
        }
        userService.create(userRepr);
        return "redirect:/login";
    }
}
