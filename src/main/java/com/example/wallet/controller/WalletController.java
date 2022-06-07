package com.example.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/hie")
public class WalletController {

    @GetMapping("/test")
    public String test() {
       return "test";
    }
}
