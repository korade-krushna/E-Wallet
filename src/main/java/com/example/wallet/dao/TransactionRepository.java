package com.example.wallet.dao;

import com.example.wallet.models.Transaction;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("from Transaction as c where c.user.id =:userId")
    List<Transaction> getTransactionsById(@Param("userId")int id);
}
