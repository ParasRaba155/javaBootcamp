package io.tntra.javabootcamp.services;

import io.tntra.javabootcamp.Enumpkg.AccountType;
import io.tntra.javabootcamp.Exceptionpkg.InsufficientBalanceException;
import io.tntra.javabootcamp.Exceptionpkg.InvalidOwnerException;
import io.tntra.javabootcamp.Exceptionpkg.MinBalanceException;
import io.tntra.javabootcamp.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class HDFCService extends AccountServiceImpl implements AccountService{

//    private HashMap<String,Account> HDFCmap = new HashMap<>();
    @Override
    public void createAccount(Account account) throws MinBalanceException {
        account.setMinBalance(BigDecimal.valueOf(1000));

        if(account.getBalance().compareTo(account.getMinBalance()) >= 0) {
            super.createAccount(account);
        }
        else{
            throw new MinBalanceException("Minimum balance of "+account.getMinBalance()+" is required");
        }
    }

    @Override
    public HashMap<String,Account> getAllAccount() {
        return super.getAllAccount();
    }

    @Override
    public Account getAccount(String ownerName) throws InvalidOwnerException {
        return super.getAccount(ownerName);
    }

    @Override
    public BigDecimal getAccountBalance(String ownerName) throws InvalidOwnerException {
        return super.getAccountBalance(ownerName);
    }

    @Override
    public BigDecimal depositAmount(String ownerName,BigDecimal amt) throws InvalidOwnerException {
        return super.depositAmount(ownerName, amt);
    }

    @Override
    public BigDecimal withdrawAmount(String ownerName, BigDecimal amt) throws InvalidOwnerException, InsufficientBalanceException {
        return super.withdrawAmount(ownerName,amt);
    }

    @Override
    public boolean checkBalance(Account account, BigDecimal amt){
        if(account.getAccType() == AccountType.CURRENT){
            account.setOverDraft(account.getBalance().multiply(BigDecimal.valueOf(0.1)));
        }
        return super.checkBalance(account,amt);
    }
}
