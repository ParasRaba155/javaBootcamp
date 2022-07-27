package io.tntra.javabootcamp.services;

import io.tntra.javabootcamp.Exceptionpkg.InsufficientBalanceException;
import io.tntra.javabootcamp.Exceptionpkg.InvalidOwnerException;
import io.tntra.javabootcamp.Exceptionpkg.MinBalanceException;
import io.tntra.javabootcamp.model.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface AccountService {
    void createAccount(Account account) throws MinBalanceException;
    HashMap<String,Account> getAllAccount();
    Account getAccount(String ownerName) throws InvalidOwnerException;
    BigDecimal getAccountBalance(String ownerName) throws InvalidOwnerException;

    Account updateAccount(String ownerName, Map<String,String> newDetails) throws InvalidOwnerException;

    BigDecimal depositAmount(String ownerName, BigDecimal amt) throws InvalidOwnerException;

    public BigDecimal withdrawAmount(String ownerName, BigDecimal amt) throws InvalidOwnerException, InsufficientBalanceException;

    public boolean isValidOwner(String ownerName);

    public boolean checkBalance(Account account,BigDecimal amt);
}
