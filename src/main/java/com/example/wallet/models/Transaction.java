package com.example.wallet.models;

import javax.persistence.*;

@Table(name = "Transaction")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransId;
    private String credit_debit;
    private String date;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction(String credit_debit , String date , int amount , User user) {
        this.credit_debit = credit_debit;
        this.date = date;
        this.amount = amount;
        this.user = user;
    }

    public int getTransId() {
        return TransId;
    }

    public void setTransId(int transId) {
        TransId = transId;
    }

    public String getCredit_debit() {
        return credit_debit;
    }

    public void setCredit_debit(String credit_debit) {
        this.credit_debit = credit_debit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = this.amount;
    }

    public Transaction() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
