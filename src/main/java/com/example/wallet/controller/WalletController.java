package com.example.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class WalletController {
    @RequestMapping("/dashboard")
    public String dashBoard() {
        return "User/dashboard";
    }

}
