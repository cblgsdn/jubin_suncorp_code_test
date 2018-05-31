package com.suncorp.app.model;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A Model class for Account details.
 * @author jubin.savla
 */
@Repository
public class Account {

    private Long accountNo;

    @NotNull(message = "First Name can't be empty")
    @Size(min = 3, message = "First name must be more than 3 characters")
    @Size(max = 15, message = "First name must not exceed 15 characters")
    private String firstName;

    @NotNull(message = "Last Name can't be empty")
    @Size(min = 3, message = "Last name must be more than 3 characters")
    @Size(max = 15, message = "Last name must not exceed 15 characters")
    private String lastName;

    @NotNull(message = "Date of Birth can't be empty")
    @Past(message = "Future Date should not be entered")
    private Date dob;

    private Date dateOfOpening;

    @NotNull(message = "Account Type must be selected")
    private String accountType;

    private BigDecimal balance;

    public Account() {

    }

    public Account(Long accountNo, String firstName, String lastName, Date dob, String accountType, BigDecimal balance) {
        this.accountNo = accountNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.dateOfOpening = new Date();
        this.accountType = accountType;
        this.balance = balance;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(Date dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
