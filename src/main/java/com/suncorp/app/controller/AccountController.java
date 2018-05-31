package com.suncorp.app.controller;

import com.suncorp.app.model.Account;
import com.suncorp.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Rest API for Accounts web services
 * @author jubin.savla
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountServiceObj;

    /**
     * For Creating new account
     * @param accountObj
     * @return Json response of type Account
     */
    @PostMapping(value = "/account/create", consumes = "application/json", produces = "application/json")
    public Account createAccount(@RequestBody @Valid Account accountObj) {
        return accountServiceObj.createAccount(accountObj);
    }

    /**
     * For Updating the type of account
     * @param accountObj
     * @return true/false as the status of transaction
     */
    @PutMapping(value = "/account/update_account_type", consumes = "application/json")
    public Boolean updateAccountType(@RequestBody Account accountObj) {
        return accountServiceObj.updateAccountType(accountObj);
    }

    /**
     * To View all the available accounts
     * @return Json response of Account list
     */
    @GetMapping(value = "/view_all_account", produces = "application/json")
    public Map<Long, Account> viewAllAccount() {
        return accountServiceObj.viewAllAccount();
    }
}
