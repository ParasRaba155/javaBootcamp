package io.tntra.javabootcamp.services;

import io.tntra.javabootcamp.Enumpkg.AccountType;
import io.tntra.javabootcamp.Exceptionpkg.InsufficientBalanceException;
import io.tntra.javabootcamp.Exceptionpkg.InvalidOwnerException;
import io.tntra.javabootcamp.Exceptionpkg.MinBalanceException;
import io.tntra.javabootcamp.model.Account;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class AccountServiceImpl implements AccountService{

    private  HashMap<String,Account> accountMap = new HashMap<>();

    @Override
    public void createAccount(Account account) throws MinBalanceException {
        accountMap.put(account.getOwnerName(),account);
    }

    @Override
    public HashMap<String,Account> getAllAccount() {
        return accountMap;
    }

    @Override
    public Account getAccount(String ownerName) throws InvalidOwnerException {
        if(isValidOwner(ownerName)){
            return accountMap.get(ownerName);
        }
        throw new InvalidOwnerException("The owner does not exist");
    }

    @Override
    public BigDecimal getAccountBalance(String ownerName) throws InvalidOwnerException {
        if(isValidOwner(ownerName)){
            return accountMap.get(ownerName).getBalance();
        }
        throw new InvalidOwnerException("The owner does not exist");
    }

    @Override
    public Account updateAccount(String ownerName, Map<String, String> newDetails) throws InvalidOwnerException {
        return null;
    }

    @Override
    public BigDecimal depositAmount(String ownerName,BigDecimal amt) throws InvalidOwnerException {
        if(isValidOwner(ownerName)){
            BigDecimal currBalance = getAccountBalance(ownerName);
            BigDecimal newBalance = currBalance.add(amt);
            accountMap.get(ownerName).setBalance(newBalance);
            return newBalance;
        }
        throw new InvalidOwnerException("The owner does not exist");
    }

    @Override
    public BigDecimal withdrawAmount(String ownerName, BigDecimal amt) throws InvalidOwnerException, InsufficientBalanceException {
        if(isValidOwner(ownerName)){
            Account acc = getAccount(ownerName);
            if(checkBalance(acc, amt)){
                BigDecimal currBalance = getAccountBalance(ownerName);
                BigDecimal newBalance = currBalance.subtract(amt);
                acc.setBalance(newBalance);
                return newBalance;
            }
            else{
                throw new InsufficientBalanceException("Insufficient Balance!!");
            }
        }
        throw new InvalidOwnerException("The owner does not exist");

    }

    public boolean isValidOwner(String ownerName) {
        return accountMap.containsKey(ownerName);
    }

    public boolean checkBalance(Account account,BigDecimal amt){
        BigDecimal deductibleAmount = account.getBalance().add(account.getOverDraft());
        return deductibleAmount.compareTo(amt) >= 0;
    }
}
