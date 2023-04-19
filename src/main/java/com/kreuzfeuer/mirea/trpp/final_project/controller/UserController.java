package com.kreuzfeuer.mirea.trpp.final_project.controller;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, BindingResult errors, Model model){
        if(errors.hasErrors()){
            return "registration";
        }
        if(userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with this login already exist!");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String visitLogin(){
        return "login";
    }

}
