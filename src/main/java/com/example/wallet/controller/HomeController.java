package com.example.wallet.controller;


import com.example.wallet.helper.Message;
import com.example.wallet.dao.UserRepository;
import com.example.wallet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

@Controller()
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/")
    public String home() {
        return "Home/home";
    }
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "Home/sign-up";
    }
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String do_register(@ModelAttribute("user") User user,
                              Model model, HttpSession session) throws Exception {
        try {
            user.setRole("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.UserRepository.save(user);
            model.addAttribute("employee", new User());
            session.setAttribute("message", new Message
                    ("Your Account has been Created", "alert-success"));
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("email-id already exist",
                    "alert-danger"));
            return "Home/sign-up";
        }
    }
    @GetMapping("/sign-in")
    public String sign(){
        return "Home/login";
    }
}

