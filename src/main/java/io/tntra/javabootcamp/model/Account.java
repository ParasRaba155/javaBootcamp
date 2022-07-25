package io.tntra.javabootcamp.model;

import Enumpkg.AccountType;

import java.math.BigDecimal;

public class Account {
    private String ownerName;
    private BigDecimal balance;
    private AccountType AccType;

    private BigDecimal minBalance = BigDecimal.valueOf(0);
    private BigDecimal overDraft = BigDecimal.valueOf(0);

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public AccountType getAccType() {
        return AccType;
    }



    public BigDecimal getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(BigDecimal overDraft) {
        this.overDraft = overDraft;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return "Account details: \nOwner:"+this.getOwnerName()+"\nBalance:"+this.getBalance()+"\nAccount type:"
                +this.getAccType();
    }

    public void setAccType(AccountType accType) {
        this.AccType = accType;
    }
}
