package com.example.wallet.models;

import javax.persistence.*;

@Table(name = "Transaction")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransId;
    private String sendTo;
    private String date;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getTransId() {
        return TransId;
    }

    public void setTransId(int transId) {
        TransId = transId;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
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
