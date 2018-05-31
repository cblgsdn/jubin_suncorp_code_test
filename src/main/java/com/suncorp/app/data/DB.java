package com.suncorp.app.data;

import com.suncorp.app.model.Account;
import com.suncorp.app.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Class to act as a database
 * @author jubin.savla
 */
@Repository
public class DB {

    Map<Long, Account> accountModelObj = new HashMap<>();
    List<Transaction> transactionModelObj = new ArrayList<>();

    public Map<Long, Account> getAccountModelObj() {
        return accountModelObj;
    }

    public void setAccountModelObj(Long key, Account accountModelObj) {
        this.accountModelObj.put(key, accountModelObj);
    }

    public List<Transaction> getTransactionModelObj() {
        return transactionModelObj;
    }

    public void setTransactionModelObj(Transaction transactionModelObj) {
        this.transactionModelObj.add(transactionModelObj);
    }
}
