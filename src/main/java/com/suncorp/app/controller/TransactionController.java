package com.suncorp.app.controller;

import com.suncorp.app.model.Transaction;
import com.suncorp.app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest API for Transactions web services
 * @author jubin.savla
 */
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionServiceObj;

    /**
     * For recording a Credit transaction
     * @param transaction
     * @return true/false as the status of transaction
     */
    @PutMapping(value = "/transaction/deposit", consumes = "application/json")
    public Boolean deposit(@RequestBody Transaction transaction) {
        return transactionServiceObj.deposit(transaction);
    }

    /**
     * For recording a Debit transaction
     * @param transaction
     * @return true/false as the status of transaction
     */
    @PutMapping(value = "/transaction/withdraw", consumes = "application/json")
    public Boolean withdraw(@RequestBody Transaction transaction) {
        return transactionServiceObj.withdraw(transaction);
    }

    /**
     * For transferring amount from one account to another
     * @param transaction
     * @return true/false as the status of transaction
     */
    @PutMapping(value = "/transaction/transfer", consumes = "application/json")
    public Boolean transferToAccount(@RequestBody Transaction transaction) {
        return transactionServiceObj.transferToAccount(transaction);
    }

    /**
     * To get the list of all transactions
     * @return Json response of List of all transaction
     */
    @GetMapping(value = "/transaction/view_all", produces = "application/json")
    public List<Transaction> getAllTransaction() {
        return transactionServiceObj.getAllTransaction();
    }
}
