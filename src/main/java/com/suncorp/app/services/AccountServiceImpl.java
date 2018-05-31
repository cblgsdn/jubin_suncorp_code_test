package com.suncorp.app.services;

import com.suncorp.app.data.DB;
import com.suncorp.app.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author jubin.savla
 * Implementation for all account related business logics
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * Creating Object for DB class.
     */
    @Autowired
    private DB db;

    /**
     * For Setting up initial data.
     */
    @PostConstruct
    public void dataSetup() {
        db.setAccountModelObj(
                1L,
                new Account(
                        1L,
                        "Jubin",
                        "Savla",
                        new Date("18/09/1991"),
                        "Savings",
                        new BigDecimal(500)
                )
        );
        db.setAccountModelObj(
                2L,
                new Account(
                        2L,
                        "Akash",
                        "Shrivastava",
                        new Date("16/04/1993"),
                        "Savings",
                        new BigDecimal(300)
                )
        );
        db.setAccountModelObj(
                3L,
                new Account(
                        3L,
                        "Ajay",
                        "Khanna",
                        new Date("12/10/1992"),
                        "current",
                        new BigDecimal(1000)
                )
        );
    }

    /**
     * Business logic for creating account.
     *
     * @param accountObj
     * @return Account type object
     */
    @Override
    public Account createAccount(Account accountObj) {
        Long accountNo = (long) db.getAccountModelObj().size() + 1;
        accountObj.setAccountNo(accountNo);
        accountObj.setDateOfOpening(new Date());
        db.setAccountModelObj(accountNo, accountObj);
        return accountObj;
    }

    /**
     * Business logic for updating account type.
     *
     * @param accountUpdateObj
     * @return Boolean
     */
    @Override
    public Boolean updateAccountType(Account accountUpdateObj) {
        Long accNo = accountUpdateObj.getAccountNo();
        Account accObj = db.getAccountModelObj().get(accNo);
        accObj.setAccountType(accountUpdateObj.getAccountType());
        db.setAccountModelObj(accNo, accObj);
        return true;
    }

    /**
     * To get list of all account.
     *
     * @return Map object
     */
    public Map<Long, Account> viewAllAccount() {
        return db.getAccountModelObj();
    }
}
