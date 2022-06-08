package com.example.wallet.controller;

import com.example.wallet.dao.TransactionRepository;
import com.example.wallet.dao.UserRepository;
import com.example.wallet.helper.Message;
import com.example.wallet.models.Transaction;
import com.example.wallet.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class WalletController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @RequestMapping("/dashboard")
    public String dashBoard(Model model, Principal principal) {
        String username = principal.getName();
        User user = this.userRepository.getUserByUsername(username);
        model.addAttribute("user", user);
        return "User/dashboard";
    }
    @PostMapping("/withdraw/{id}")
    public String update(@PathVariable("id") Integer id,
                         HttpSession session, @RequestParam("withdraw_req") Integer Ammount) throws Exception {
        try {
            User exist = this.userRepository.findById(id).get();
            int prev = exist.getCurBalance();
            int new_amm = prev - Ammount;
            if ( new_amm < 0) throw new Exception("Not Enough Money");
            exist.setCurBalance(new_amm);
            session.setAttribute("message" , new Message
                    ("Ammount Withdrawn" , "alert-success"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Transaction credited = new Transaction("Credited" , dtf.format(now) , Ammount , exist);
            this.transactionRepository.save(credited);
            this.userRepository.save(exist);
        } catch (Exception e) {
            session.setAttribute("message" , new Message
                    (e.getMessage() , "alert-danger"));
        }
        return "redirect:/user/dashboard";
    }
    @PostMapping("/add-money/{id}")
    public String addMoney(@PathVariable("id") Integer id,
                         HttpSession session, @RequestParam("withdraw_req") Integer Ammount) throws Exception {
        try {
            User exist = this.userRepository.findById(id).get();
            int prev = exist.getCurBalance();
            int new_amm = prev + Ammount;
            exist.setCurBalance(new_amm);
            session.setAttribute("message" , new Message
                    ("Money Added" , "alert-success"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Transaction debited = new Transaction("Debited" , dtf.format(now) , Ammount , exist);
            this.transactionRepository.save(debited);
            this.userRepository.save(exist);
        } catch (Exception e) {
            session.setAttribute("message" , new Message
                    (e.getMessage() , "alert-success"));
        }
        return "redirect:/user/dashboard";
    }
    @GetMapping("/delet-acc/{id}") // controller for deleting ice cream
    public String deletIceCream(@PathVariable("id") Integer id, Model model){
        User user = this.userRepository.findById(id).get();
        this.userRepository.delete(user);
        return "redirect:/logout";
    }
    @GetMapping("/get-tra/{id}")
    public String getTransactions(@PathVariable("id") Integer id, Model model){
        List<Transaction> transactions = this.transactionRepository.getTransactionsById(id);
        model.addAttribute("transactions", transactions);
        return "User/history";
    }
}
