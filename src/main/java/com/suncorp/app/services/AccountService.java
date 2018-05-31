package com.suncorp.app.services;

import com.suncorp.app.model.Account;

import java.util.Map;

/**
 * @author jubin.savla
 * Account Service Interface
 * Interface for all Account related business logic
 */
public interface AccountService {

    Account createAccount(Account accountObj);

    Boolean updateAccountType(Account accountObj);

    Map<Long, Account> viewAllAccount();
}
