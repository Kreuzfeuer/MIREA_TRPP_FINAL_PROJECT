package com.kreuzfeuer.mirea.trpp.final_project.controller;

import com.kreuzfeuer.mirea.trpp.final_project.entity.User;
import com.kreuzfeuer.mirea.trpp.final_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult errors, Model model){
        if(errors.hasErrors()){
           model.addAttribute("errors", errors.getAllErrors());
            return "registration";
        }
        if(!user.getPassword().equals(user.getConfirmedPassword())) {
            model.addAttribute("errorMessage", "Confirmed password and password are not equal!");
            return "registration";
        }
        boolean flag = userService.createUser(user);

        if(!flag) {
            model.addAttribute("errorMessage", "User with this login already exist!");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String visitLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

}
