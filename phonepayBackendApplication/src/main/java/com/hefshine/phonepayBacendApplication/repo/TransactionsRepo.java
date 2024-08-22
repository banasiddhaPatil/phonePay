package com.hefshine.phonepayBacendApplication.repo;

import com.hefshine.phonepayBacendApplication.model.Transactions;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions,Integer> {
}
