package com.suncorp.app.services;

import com.suncorp.app.model.Transaction;

import java.util.List;

/**
 * @author jubin.savla
 * Transaction Service Interface
 * Interface for all the transactions
 */
public interface TransactionService {

    Boolean deposit(Transaction transaction);

    Boolean withdraw(Transaction transaction);

    Boolean transferToAccount(Transaction transaction);

    List<Transaction> getAllTransaction();

}
