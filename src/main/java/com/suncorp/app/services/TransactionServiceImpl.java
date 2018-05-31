package com.suncorp.app.services;

import com.suncorp.app.data.DB;
import com.suncorp.app.model.Account;
import com.suncorp.app.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jubin.savla
 * Implementation for all transaction related business logics
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    DB db;

    private static final String SUCCESS_STATUS = "success";
    private static final String FAILED_STATUS = "failed";
    private static final String AMT_CREDIT = "credit";
    private static final String AMT_DEBIT = "debit";

    /**
     * Business logic for depositing money to some account
     * @param transactionObj
     * @return true/false as a status
     */
    @Override
    public Boolean deposit(Transaction transactionObj) {
        Account accObj = db.getAccountModelObj().get(transactionObj.getAccountNo());
        accObj.setBalance(accObj.getBalance().add(transactionObj.getAmount()));

        db.setAccountModelObj(transactionObj.getAccountNo(), accObj);
        addTransaction(transactionObj, AMT_CREDIT, SUCCESS_STATUS);
        return true;
    }

    /**
     * Business logic for withdrawing money to some account
     * @param transactionObj
     * @return true/false as a status
     */
    @Override
    public Boolean withdraw(Transaction transactionObj) {
        Account accObj = db.getAccountModelObj().get(transactionObj.getAccountNo());

        if (accObj.getBalance().compareTo(transactionObj.getAmount()) >= 0) {
            accObj.setBalance(accObj.getBalance().subtract(transactionObj.getAmount()));
            db.setAccountModelObj(transactionObj.getAccountNo(), accObj);
            addTransaction(transactionObj, AMT_DEBIT, SUCCESS_STATUS);
            return true;
        } else {
            addTransaction(transactionObj, AMT_DEBIT, FAILED_STATUS);
            return false;
        }
    }

    /**
     * Business logic for transferring money from one account to another
     * @param transactionObj
     * @return true/false as a status
     */
    @Override
    public Boolean transferToAccount(Transaction transactionObj) {

        Account fromAccObj = db.getAccountModelObj().get(transactionObj.getFromAccount());
        Account toAccObj = db.getAccountModelObj().get(transactionObj.getToAccount());

        if (fromAccObj.getBalance().compareTo(transactionObj.getAmount()) >= 0) {
            fromAccObj.setBalance(fromAccObj.getBalance().subtract(transactionObj.getAmount()));
            db.setAccountModelObj(transactionObj.getFromAccount(), fromAccObj);

            toAccObj.setBalance(toAccObj.getBalance().add(transactionObj.getAmount()));
            db.setAccountModelObj(transactionObj.getToAccount(), toAccObj);

            transactionObj.setAccountNo(transactionObj.getFromAccount());
            addTransaction(transactionObj, AMT_DEBIT, SUCCESS_STATUS);
            transactionObj.setAccountNo(transactionObj.getToAccount());
            addTransaction(transactionObj, AMT_CREDIT, SUCCESS_STATUS);
            return true;
        } else {
            transactionObj.setAccountNo(transactionObj.getFromAccount());
            addTransaction(transactionObj, AMT_DEBIT, FAILED_STATUS);
            transactionObj.setAccountNo(transactionObj.getToAccount());
            addTransaction(transactionObj, AMT_CREDIT, FAILED_STATUS);
            return false;
        }
    }

    /**
     * A function to record the transaction to database when a user deposit/withdraw/transfer
     * @param transactionObj
     * @param transactionType
     * @param status
     */
    public void addTransaction(Transaction transactionObj, String transactionType, String status) {
        Long transactionId = (long) db.getTransactionModelObj().size() + 1;
        Transaction newTransactionObj = new Transaction(transactionId, transactionObj.getAccountNo(), transactionObj.getFromAccount(), transactionObj.getToAccount(), transactionObj.getAmount(), transactionType, status);
        db.setTransactionModelObj(newTransactionObj);
    }

    /**
     * To get List of all transactions
     * @return List type object
     */
    @Override
    public List<Transaction> getAllTransaction() {
        return db.getTransactionModelObj();
    }
}
