package com.example.wallet.controller;

import com.example.wallet.dao.UserRepository;
import com.example.wallet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class WalletController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/dashboard")
    public String dashBoard(Model model, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.getUserByUsername(username);
        model.addAttribute("user", user);
        return "User/dashboard";
    }
}
